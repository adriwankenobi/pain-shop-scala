package test

import org.junit.Rule
import org.junit.rules.ExpectedException
import org.junit.Assert._
import org.junit.Test
import com.acerete.exception.ReadingFileException
import com.acerete.input.InputReader
import scala.annotation.meta.getter
import com.acerete.exception.WrongFormatException
import com.acerete.input.Input
import com.acerete.service.ColorType

class TestOutput extends SetupTest {
  
  @Test
  @throws(classOf[Exception])
  def testSomeOutput() {
    var result: String = OUTPUT_WRITER.writeOutputIntoStdout(VALID_OUTPUT)
    assertEquals(VALID_OUTPUT_RESULT, result)
  }
  
}