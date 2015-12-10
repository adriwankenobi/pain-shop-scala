package com.acerete.output

trait OutputWriter {

  /**
   * Writes output content into standard output and returns it
   */
  def writeOutputIntoStdout(outputList: List[Output]): String
}