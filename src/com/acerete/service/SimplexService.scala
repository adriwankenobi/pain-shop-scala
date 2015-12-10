package com.acerete.service

import com.acerete.exception.WrongSolutionException
import com.acerete.output.Output
import com.acerete.input.Input
import com.acerete.service.ColorType._
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction
import org.apache.commons.math3.optim.linear.LinearConstraint
import org.apache.commons.math3.optim.linear.SimplexSolver
import org.apache.commons.math3.optim.PointValuePair
import org.apache.commons.math3.optim.linear.LinearConstraintSet
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType
import org.apache.commons.math3.optim.linear.NonNegativeConstraint
import org.apache.commons.math3.optim.linear.NoFeasibleSolutionException
import org.apache.commons.math3.optim.linear.Relationship
import com.acerete.exception.ColorTypeNotFoundException

class SimplexService(input: Input) extends Service {
  
  private val NO_INT_ERROR: String = "Solution should be int number"
  private val NO_ZERO_OR_ONE_ERROR: String = "Solution should be 0 or 1"
  private val TOO_MANY_BATCHES: String = "There are more offered batches than permitted"
	
  var _caseId: Int = input.caseId
  var _nColors: Int = input.nColors
  var _customerSet: Set[Customer] = input.customerSet
  
  var _colorTypes: List[ColorType.Value] = ColorType.values.toList
  var _nTypes: Int = _colorTypes.size
  var _nCombinations: Int = _nTypes * _nColors;
  
  @throws(classOf[WrongSolutionException])
  def serve(): Output = {
    
    // Create the target function (maximize cheapest color type)
    var coefficients: Array[Double] = generateCoefficients(_nColors)
    var targetFunction: LinearObjectiveFunction = new LinearObjectiveFunction(coefficients, 0);
    
    // Define list of constraints
    var constraints: java.util.List[LinearConstraint] = new java.util.ArrayList()
    
    // Add color constraints (only one type per color)
    addColorConstrainsts(constraints)
    
    // Add customers specific constraints
    for (customer <- _customerSet) {
      addCustomerConstraint(constraints, customer)
    }
    
    // Create simplex solver
    var solver: SimplexSolver = new SimplexSolver()
    
    // Execute the simplex algorithm
    try {
      var optSolution: PointValuePair = solver.optimize(
          targetFunction,
          new LinearConstraintSet(constraints),
          GoalType.MAXIMIZE,
          new NonNegativeConstraint(true)
       )
       
       var solution: Array[Double] = optSolution.getPoint()
       return generateOutput(solution)
    }
    catch {
      // No solution, return empty output
      case e: NoFeasibleSolutionException => return new Output(_caseId)
    }
  }
  
  /**
   * PRIVATE HELPER METHODS
   */
  
  /**
   * Generate coefficients
   * Setting 1 to cheapest color types, 0 to others
   * One element per color type, per color
   */
  private def generateCoefficients(nColors: Int): Array[Double] = {
    
    var coefficients: Array[Double] = new Array[Double](_nCombinations)
    for (i <- 0 to _nCombinations - 1 if i % _nTypes == 0; 
    	 j <- 0 to _nTypes - 1 if _colorTypes.apply(j) == ColorType.cheapest()) {
      coefficients.update(i + j, 1)
    }
    return coefficients;
  }
  
  /**
   * Add color constraints
   * Setting 1 to not repeatable color types, 0 to others
   * One element per color type, per color
   */
  private def addColorConstrainsts(constraints: java.util.List[LinearConstraint]) {
    
    for(i <- 0 to _nColors - 1) {
      var rule: Array[Double] = new Array[Double](_nCombinations)
      for(j <- 0 to _nCombinations - 1 if j == _nTypes * i || j == _nTypes * i + 1) {
        rule.update(j, 1)
      }
      constraints.add(new LinearConstraint(rule, Relationship.EQ, Color.BATCHES_OFFERED_PER_COLOR))
    }
  }
  
  /**
   * Add customer constraint
   * Setting 1 to liked colors types, 0 to others
   * One element per color type, per color 
   */
  private def addCustomerConstraint(constraints: java.util.List[LinearConstraint], customer: Customer) {
    
    var rule: Array[Double] = new Array[Double](_nCombinations)
    
    for (i <- 0 to rule.length) {
      var colorId: Int = (i / _nTypes) + 1
      var cType: ColorType.Value = _colorTypes.apply(i % _nTypes)
      var color: Color = new Color(colorId, cType)
      if (customer.likes(color)) {
        rule.update(i, 1)
      }
    }
    constraints.add(new LinearConstraint(rule, Relationship.GEQ, Customer.MIN_MATCHES_TO_SATISFY))
  }

  /**
   * Generates output from solution from simplex algorithm
   */
  @throws(classOf[WrongSolutionException])
  private def generateOutput(solution: Array[Double]): Output = {
    
    var output: Output = new Output(_caseId)
    
    for (i <- 0 to _nCombinations - 1 if i % _nTypes == 0) {
      var totalMatches: Int = 0
      var matches: List[ColorType] = List()
      for (j <- 0 to _nTypes - 1) {
        if (solution.apply(i + j) % 1 != 0) {
          throw new WrongSolutionException(NO_INT_ERROR)
        }
        if (solution.apply(i + j) != 0 && solution.apply(i + j) != 1) {
          throw new WrongSolutionException(NO_ZERO_OR_ONE_ERROR)
        }
        if (solution.apply(i + j) == 1) {
          totalMatches += 1
          try {
            matches = matches ::: List(ColorType.byId((i + j) % _nTypes))
          }
          catch {
            case e: ColorTypeNotFoundException => throw new WrongSolutionException(e.getMessage())
          }
        }
      }
      if (totalMatches != Color.BATCHES_OFFERED_PER_COLOR) {
        throw new WrongSolutionException(TOO_MANY_BATCHES)
      }
      output.addColorList(matches)
    }
    return output
  }
}