package test

import org.junit.Test
import org.junit.Assert._
import com.acerete.output.Output
import com.acerete.service.Service
import com.acerete.service.SimplexService

class TestService extends SetupTest {
  
  @Test
  @throws(classOf[Exception])
  def testSomeCustomers() {
    
    var outputList: List[Output] = List()
    for (input <- VALID_INPUT) {
      var service: Service = new SimplexService(input)
      var output: Output = service.serve()
      outputList = outputList ::: List(output)
    }
    
    assertEquals(outputList.size, VALID_INPUT.size)
    for (output <- outputList) {
      assertTrue(VALID_OUTPUT.contains(output))
    }
  }

}