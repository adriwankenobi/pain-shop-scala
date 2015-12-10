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

class TestInput extends SetupTest {

  @(Rule @getter)
  val expectedEx: ExpectedException = ExpectedException.none
  
  @Test(expected = classOf[ReadingFileException])
  @throws(classOf[Exception])
  def testNoFile() {
    expectedEx.expectMessage(InputReader.FILE_NOT_FOUND)
    INPUT_READER.readInputFromFile(NO_FILE)
  }
  
  @Test(expected = classOf[NumberFormatException])
  @throws(classOf[Exception])
  def testNoInteger() {
    INPUT_READER.readInputFromFile(NO_INT)
  }
  
  @Test(expected = classOf[NumberFormatException])
  @throws(classOf[Exception])
  def testNegativeInteger(){
    INPUT_READER.readInputFromFile(NEGATIVE_INT)
  }
	
  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testNoNTestCases() {
    expectedEx.expectMessage(InputReader.N_TEST_CASES_NOT_FOUND)
    INPUT_READER.readInputFromFile(NO_N_TEST_CASES_FILE)
  }
	
  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testNoNColors() {
    expectedEx.expectMessage(InputReader.N_COLORS_NOT_FOUND)
    INPUT_READER.readInputFromFile(NO_N_COLORS_FILE)
  }
	
  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testNoNCustomers() {
    expectedEx.expectMessage(InputReader.N_CUSTOMERS_NOT_FOUND)
    INPUT_READER.readInputFromFile(NO_N_CUSTOMERS_FILE)
  }
  
  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testLimits1() {
    expectedEx.expectMessage(InputReader.LIMITS_EXCEEDED)
    INPUT_READER.readInputFromFile(LIMITS_EXCEEDED_1_FILE)
  }
  
  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testLimits2() {
    expectedEx.expectMessage(InputReader.LIMITS_EXCEEDED)
    INPUT_READER.readInputFromFile(LIMITS_EXCEEDED_2_FILE)
  }
  
  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testLimits3() {
    expectedEx.expectMessage(InputReader.LIMITS_EXCEEDED)
    INPUT_READER.readInputFromFile(LIMITS_EXCEEDED_3_FILE)
  }
  
  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testNoNLikes() {
    expectedEx.expectMessage(InputReader.N_LIKES_NOT_FOUND)
    INPUT_READER.readInputFromFile(NO_N_LIKES_FILE)
  }

  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testWrongCustomerFormat() {
    expectedEx.expectMessage(InputReader.WRONG_CUSTOMER_FORMAT)
    INPUT_READER.readInputFromFile(WRONG_CUSTOMER_FORMAT_FILE)
  }
  
  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testWrongColorTypeId() {
    expectedEx.expectMessage(ColorType.UNKNOWN_COLOR_TYPE_ID)
    INPUT_READER.readInputFromFile(WRONG_COLOR_TYPE_ID_FILE)
  }
  
  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testWrongColor() {
    expectedEx.expectMessage(InputReader.WRONG_COLOR)
    INPUT_READER.readInputFromFile(WRONG_COLOR_FILE)
  }
	
  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testAlreadyLikedColor() {
    expectedEx.expectMessage(InputReader.ALREADY_LIKED_COLOR)
    INPUT_READER.readInputFromFile(ALREADY_LIKED_COLOR_FILE)
  }
  
  @Test(expected = classOf[WrongFormatException])
  @throws(classOf[Exception])
  def testAlreadyLikedMatte() {
    expectedEx.expectMessage(InputReader.ALREADY_LIKED_MATTE)
    INPUT_READER.readInputFromFile(ALREADY_LIKED_MATTE_FILE)
  }
  
  @Test
  @throws(classOf[Exception])
  def testValidFile() {
    var inputSet: Set[Input] = INPUT_READER.readInputFromFile(VALID_FILE)
    assertEquals(inputSet, VALID_INPUT)
  }
  
}