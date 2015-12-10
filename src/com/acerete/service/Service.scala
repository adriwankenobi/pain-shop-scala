package com.acerete.service

import com.acerete.exception.WrongSolutionException
import com.acerete.output.Output

trait Service {

  /**
   * Generates an output
   */
  @throws(classOf[WrongSolutionException])
  def serve(): Output
}