package test

import com.acerete.service.ColorType
import com.acerete.service.ColorType._
import com.acerete.input.Input
import com.acerete.output.Output
import com.acerete.service.Customer
import com.acerete.service.Color
import org.junit.Test
import org.junit.Assert._
import com.acerete.service.Service
import com.acerete.service.SimplexService
import com.acerete.exception.WrongSolutionException

class TestLimits extends SetupTest {
  
  val TEST_COLOR_TYPE: ColorType = ColorType.MATTE
  
  val SMALL_DATASET_N_CASES: Int = Input.N_CASES_MAX
  val SMALL_DATASET_N_COLORS: Int = Color.N_COLORS_LIMIT
  val SMALL_DATASET_N_CUSTOMERS: Int = Customer.N_CUSTOMERS_LIMIT
  
  val LARGE_DATASET_N_CASES: Int = Input.N_CASES_LIMIT
  val LARGE_DATASET_N_COLORS: Int = Color.N_COLORS_MAX
  val LARGE_DATASET_N_CUSTOMERS: Int = Customer.N_CUSTOMERS_MAX;
  
  @Test
  @throws(classOf[Exception])
  def testSmallDataset() {
    var validInputList: Set[Input] = generateInput(SMALL_DATASET_N_CASES, SMALL_DATASET_N_COLORS, SMALL_DATASET_N_CUSTOMERS)
    var expectedOutputList: List[Output] = generateOutput(SMALL_DATASET_N_CASES, SMALL_DATASET_N_COLORS, SMALL_DATASET_N_CUSTOMERS)
    doTest(validInputList, expectedOutputList)
  }
  
  @Test
  @throws(classOf[Exception])
  def testLargeDataset() {
    var validInputList: Set[Input] = generateInput(LARGE_DATASET_N_CASES, LARGE_DATASET_N_COLORS, LARGE_DATASET_N_CUSTOMERS)
    var expectedOutputList: List[Output] = generateOutput(LARGE_DATASET_N_CASES, LARGE_DATASET_N_COLORS, LARGE_DATASET_N_CUSTOMERS)
    doTest(validInputList, expectedOutputList)
  }
  
  private def generateInput(nCases: Int, nColors: Int, nCustomers: Int): Set[Input] = {
    var inputSet: Set[Input] = Set()
    for (i <- 0 to nCases - 1) {
      var input: Input = new Input(i, nColors)
      for (j <- 0 to nCustomers - 1) {
        var customer: Customer = new Customer()
        customer.addLike(new Color((j % nColors) + 1, TEST_COLOR_TYPE))
        input.addCustomer(customer)
      }
      inputSet += input
    }
    return inputSet
  }

  private def generateOutput(nCases: Int, nColors: Int, nCustomers: Int): List[Output] = {
    var outputList: List[Output] = List()
    for (i <- 0 to nCases - 1) {
      var output: Output = new Output(i)
      for (j <- 0 to nColors - 1) {
        if (output.colors.size < nCustomers) {
          output.addColor(TEST_COLOR_TYPE)
        }
        else {
          output.addColor(ColorType.cheapest())
        }
      }
      outputList = outputList ::: List(output)
    }
    return outputList
  }
  
  @throws(classOf[WrongSolutionException])
  private def doTest(inputSet: Set[Input], expectedOutputList: List[Output]) {
    var outputList: List[Output] = List()
    for (input <- inputSet) {
      var service: Service = new SimplexService(input)
      var output: Output = service.serve()
      outputList = outputList ::: List(output)
    }
    
    assertEquals(outputList.size, inputSet.size)
    for (output <- outputList) {
      assertTrue(expectedOutputList.contains(output))
    }
  }

}