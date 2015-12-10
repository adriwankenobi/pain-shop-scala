package com.acerete.shop

import com.acerete.exception.ReadingFileException
import com.acerete.exception.WrongFormatException
import com.acerete.exception.WrongSolutionException
import java.util.concurrent.ExecutionException

object Shop {
  val NUM_WORKERS: Int = 10;
}

trait Shop {
  
  /**
   * Writes in standard output the result of serving the customers from file and returns it
   */
  @throws(classOf[ReadingFileException])
  @throws(classOf[WrongFormatException])
  @throws(classOf[WrongSolutionException])
  @throws(classOf[InterruptedException])
  @throws(classOf[ExecutionException])
  def satisfy(fileName: String): String

}