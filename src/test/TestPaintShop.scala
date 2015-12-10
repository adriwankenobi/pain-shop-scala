package test

import org.junit.Test
import org.junit.Assert._

class TestPaintShop extends SetupTest {
  
  @Test
  @throws(classOf[Exception])
  def testSomeCustomers() {
    var result: String = SHOP.satisfy(VALID_FILE)
    assertEquals(VALID_OUTPUT_RESULT, result)
  }
	
  @Test
  @throws(classOf[Exception])
  def testOtherCustomers() {
    var result: String = SHOP.satisfy(OTHER_VALID_FILE)
    assertEquals(OTHER_VALID_OUTPUT_RESULT, result)
  }

}