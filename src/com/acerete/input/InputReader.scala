package com.acerete.input

import com.acerete.exception.ReadingFileException
import com.acerete.exception.WrongFormatException

object InputReader {
  
  /**
   * Input limits
   */
  val MAX_LIKES_PER_CASE: Int = 3000
	
  /**
   * Error messages
   */
  val FILE_NOT_FOUND: String = "File not found"
  val N_TEST_CASES_NOT_FOUND: String = "Number of test cases not found"
  val N_COLORS_NOT_FOUND: String = "Number of colors not found"
  val N_CUSTOMERS_NOT_FOUND: String = "Number of customers not found"
  val LIMITS_EXCEEDED: String = "Limits exceeded"
  val N_LIKES_NOT_FOUND: String = "Number of likes not found"
  val WRONG_CUSTOMER_FORMAT: String = "Wrong customer format"
  val WRONG_COLOR: String = "Wrong color"
  val ALREADY_LIKED_COLOR: String = "Already liked color"
  val ALREADY_LIKED_MATTE: String = "Already liked matte color"
  val TOO_MANY_LIKES: String = "Too many likes"
	
  /**
   * Delimiter character
   */
  val DELIM: String = " ";
}

trait InputReader {

  /**
   * Reads input content from file
   */
  @throws(classOf[ReadingFileException])
  @throws(classOf[WrongFormatException])
  def readInputFromFile(fileName: String): Set[Input]
	
}