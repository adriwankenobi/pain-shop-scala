package com.acerete.output

import scala.util.Sorting

object StdOutputWriter extends OutputWriter {
  
  def writeOutputIntoStdout(outputList: List[Output]): String = {
    
    var builder: StringBuilder = new StringBuilder()
    
    // Print list sorted by case id
    var sortedList: List[Output] = outputList.sortWith(_.caseId < _.caseId)
    
    for( i <- 0 to sortedList.length-1) {
     
      var sb: StringBuilder = new StringBuilder()
      sb.append("Case #")
      sb.append(i + 1)
      sb.append(": ")
     
      if (!sortedList.apply(i).hasColors()) {
        sb.append("IMPOSSIBLE")
      }
      else {
        for (color <- sortedList.apply(i).colors()) {
          sb.append(color.id);
          sb.append(" ");
        }
      }
     
      builder.append(sb)
      builder.append("\r\n")
     
    }
    
    var result: String = builder.toString
    println(result)
    return result;
  }

}