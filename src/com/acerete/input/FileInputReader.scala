package com.acerete.input

import com.acerete.exception.ReadingFileException
import com.acerete.exception.WrongFormatException
import com.acerete.service.Customer
import scala.io.Source
import com.acerete.service.Color
import com.acerete.service.ColorType
import com.acerete.exception.ColorTypeNotFoundException
import java.io.IOException

object FileInputReader extends InputReader {
  
  @throws(classOf[ReadingFileException])
  @throws(classOf[WrongFormatException])
  def readInputFromFile(fileName: String): Set[Input] = {
    
    var inputSet: Set[Input] = null
    
    try {
      var resource: Source = Source.fromFile(fileName)
      
      if (resource == null) {
        throw new ReadingFileException(InputReader.FILE_NOT_FOUND);
      }
      
      inputSet = readFromFile(resource);
    }
    catch {
      case e: IOException => throw new ReadingFileException(e.getMessage());
    }
    
    return inputSet
  }
  
  /**
   * PRIVATE HELPER METHODS
   */
  @throws(classOf[WrongFormatException])
  private def readFromFile(resource: Source): Set[Input] = {
		
    var inputSet: Set[Input] = Set()
	
    var lines: Iterator[String] = resource.getLines
    
	if (!lines.hasNext) {
		throw new WrongFormatException(InputReader.N_TEST_CASES_NOT_FOUND)
	}
		
	var nCases: Int = parsePositiveInt(lines.next, Input.N_CASES_MIN, Input.N_CASES_MAX)
		
	for( i <- 0 to nCases-1) {
	  var input: Input = readCase(i, lines, nCases)
	  inputSet += input
	}
		
	return inputSet;
  }
  
  @throws(classOf[WrongFormatException])
  private def readCase(caseId: Int, lines: Iterator[String], nCases: Int): Input = {
	
    if (!lines.hasNext) {
	  throw new WrongFormatException(InputReader.N_COLORS_NOT_FOUND)
	}
		
	var nColors: Int = parsePositiveInt(lines.next, Color.N_COLORS_MIN, Color.N_COLORS_MAX)
			
	if (!lines.hasNext) {
	  throw new WrongFormatException(InputReader.N_CUSTOMERS_NOT_FOUND)
	}
				
	var nCustomers: Int = parsePositiveInt(lines.next, Customer.N_CUSTOMERS_MIN, Customer.N_CUSTOMERS_MAX)
	
	if (nCases > Input.N_CASES_LIMIT && 
				(nColors > Color.N_COLORS_LIMIT || nCustomers > Customer.N_CUSTOMERS_LIMIT)) {
			throw new WrongFormatException(InputReader.LIMITS_EXCEEDED);
		}
	
	var input: Input = new Input(caseId, nColors)
				
	var totalLikes: Int = 0;
		
	for ( _ <- 0 to nCustomers-1) {
	  var customer: Customer = readCustomer(lines, nColors)
	  totalLikes += customer.likesSize
	
	  if (totalLikes > InputReader.MAX_LIKES_PER_CASE) {
	    throw new WrongFormatException(InputReader.TOO_MANY_LIKES)
	  }
	  
	  input.addCustomer(customer)
	}
				
	return input
  }
  
  @throws(classOf[WrongFormatException])
  private def readCustomer(lines: Iterator[String], nColors: Int): Customer = {
		
	if (!lines.hasNext) {
	  throw new WrongFormatException(InputReader.N_LIKES_NOT_FOUND)
	}
		
	var line: Array[String] = lines.next.split(InputReader.DELIM)
	var nLikes: Int = parsePositiveInt(line.apply(0), 1)
		
	if (line.length != nLikes * 2 + 1) {
	  throw new WrongFormatException(InputReader.WRONG_CUSTOMER_FORMAT)
	}
		
	var customer: Customer = new Customer()
				
	for (i <- 0 to nLikes-1) {
	  var color: Color = readColor(line, i)
	  
	  if (color.id() <= 0 || color.id() > nColors) {
		throw new WrongFormatException(InputReader.WRONG_COLOR)
	  }
			
	  if (customer.likes(color)) {
	    throw new WrongFormatException(InputReader.ALREADY_LIKED_COLOR)
	  }
			
	  if (color.cType() == ColorType.MATTE && customer.likesType(ColorType.MATTE)) {
	    throw new WrongFormatException(InputReader.ALREADY_LIKED_MATTE)
	  }
	  
	  customer.addLike(color)
	}
				
	return customer
  }
  
  @throws(classOf[WrongFormatException])
  private def readColor(line: Array[String], idx: Int): Color = {
    
    var colorId: Int = parsePositiveInt(line.apply(2 * idx + 1), 1)
    var colorTypeId: Int = parsePositiveInt(line.apply(2 * idx + 2), 0)
		
	try {
	  return new Color(colorId, ColorType.byId(colorTypeId))
	}
	catch {
	  case e: ColorTypeNotFoundException => throw new WrongFormatException(e.getMessage())
	}
  }
  
  private def parsePositiveInt(string: String, minValue: Int): Int = {
    return parsePositiveInt(string, minValue, Integer.MAX_VALUE)
  }
	
  private def parsePositiveInt(string: String, minValue: Int, maxValue: Int): Int = {
	var number: Int = string.toInt
		
	if (number < 0) {
	  throw new NumberFormatException()
	}
		
	if (number < minValue) {
	  throw new NumberFormatException()
	}
		
	if (number > maxValue) {
	  throw new NumberFormatException()
	}
		
	return number
  }
  
}