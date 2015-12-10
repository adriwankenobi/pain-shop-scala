package com.acerete.shop

import com.acerete.exception.ReadingFileException
import com.acerete.exception.WrongFormatException
import com.acerete.exception.WrongSolutionException
import com.acerete.input.InputReader
import com.acerete.output.OutputWriter
import com.acerete.input.FileInputReader
import com.acerete.output.StdOutputWriter
import com.acerete.input.Input
import com.acerete.output.Output
import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.ExecutionException
import scala.collection.JavaConversions._
import com.acerete.service.Service
import com.acerete.service.SimplexService

object PaintShop extends Shop {

  val inputReader: InputReader = FileInputReader
  val outputWriter: OutputWriter = StdOutputWriter
	
  @throws(classOf[ReadingFileException])
  @throws(classOf[WrongFormatException])
  @throws(classOf[WrongSolutionException])
  @throws(classOf[InterruptedException])
  @throws(classOf[ExecutionException])
  def satisfy(fileName: String): String = {
    
    // Read from file
    var inputSet: Set[Input] = inputReader.readInputFromFile(fileName)
    
    // Prepare output
    var outputList: List[Output] = List()
    
    // Parallelize work among workers
    var executorService: ExecutorService = Executors.newFixedThreadPool(Shop.NUM_WORKERS)
    
    var callables: java.util.Set[Callable[Output]] = new java.util.HashSet()
    for (input <- inputSet) {
      callables.add(new Worker(input))
    }
    
    var futures: java.util.List[Future[Output]] = executorService.invokeAll(callables)
    
    for(future <- futures) {
      outputList = outputList ::: List(future.get())
    }
    
    // Print output
    return outputWriter.writeOutputIntoStdout(outputList);
  }
  
  private class Worker(input: Input) extends Callable[Output] {
    
    var _service: Service = new SimplexService(input)
    
    @throws(classOf[Exception])
    override def call(): Output = return _service.serve()
  }
  
}