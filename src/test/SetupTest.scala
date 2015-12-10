package test

import com.acerete.input.Input
import com.acerete.service.Customer
import com.acerete.service.ColorType
import com.acerete.service.Color
import com.acerete.output.Output
import com.acerete.input.InputReader
import com.acerete.output.StdOutputWriter
import com.acerete.output.OutputWriter
import com.acerete.input.FileInputReader
import com.acerete.shop.PaintShop
import com.acerete.shop.Shop

class SetupTest {

  val NO_FILE: String = "src/test/files/no.txt"
  val NO_INT: String = "src/test/files/no_int.txt"
  val NEGATIVE_INT: String = "src/test/files/negative_int.txt"
  val NO_N_TEST_CASES_FILE: String = "src/test/files/no_n_test_cases.txt"
  val NO_N_COLORS_FILE: String = "src/test/files/no_n_colors.txt"
  val NO_N_CUSTOMERS_FILE: String = "src/test/files/no_n_customers.txt"
  val LIMITS_EXCEEDED_1_FILE: String = "src/test/files/limits_exceeded_1.txt"
  val LIMITS_EXCEEDED_2_FILE: String = "src/test/files/limits_exceeded_2.txt"
  val LIMITS_EXCEEDED_3_FILE: String = "src/test/files/limits_exceeded_3.txt"
  val NO_N_LIKES_FILE: String = "src/test/files/no_n_likes.txt"
  val WRONG_CUSTOMER_FORMAT_FILE: String = "src/test/files/wrong_customer_format.txt"
  val WRONG_COLOR_TYPE_ID_FILE: String = "src/test/files/wrong_color_type_id.txt"
  val WRONG_COLOR_FILE: String = "src/test/files/wrong_color.txt"
  val ALREADY_LIKED_COLOR_FILE: String = "src/test/files/already_liked_color.txt"
  val ALREADY_LIKED_MATTE_FILE: String = "src/test/files/already_liked_matte.txt"
  val VALID_FILE: String = "src/test/files/valid.txt"
  val OTHER_VALID_FILE: String = "src/test/files/other_valid.txt"
	
  val VALID_OUTPUT_RESULT: String = "Case #1: 1 0 0 0 0 \r\nCase #2: IMPOSSIBLE\r\n"
  val OTHER_VALID_OUTPUT_RESULT: String = "Case #1: 0 0 0 \r\n"
	
  val INPUT_READER: InputReader = FileInputReader
  val OUTPUT_WRITER: OutputWriter = StdOutputWriter
	
  val SHOP: Shop = PaintShop
	
  // Matches values on 'valid.txt'
  var input1 = new Input(0, 5)
  var customerA1: Customer = new Customer()
  customerA1.addLike(new Color(1, ColorType.MATTE))
  var customerA2: Customer = new Customer()
  customerA2.addLike(new Color(1, ColorType.GLOSSY))
  customerA2.addLike(new Color(2, ColorType.GLOSSY))
  var customerA3: Customer = new Customer()
  customerA3.addLike(new Color(5, ColorType.GLOSSY))
  input1.addCustomer(customerA1)
  input1.addCustomer(customerA2)
  input1.addCustomer(customerA3)
		
  var input2:Input = new Input(1, 1)
  var customerB1: Customer = new Customer()
  customerB1.addLike(new Color(1, ColorType.GLOSSY))
  var customerB2: Customer = new Customer()
  customerB2.addLike(new Color(1, ColorType.MATTE))
  input2.addCustomer(customerB1)
  input2.addCustomer(customerB2)
  
  val VALID_INPUT: Set[Input] = Set(input1, input2)

  // Matches values on 'VALID_OUTPUT_RESULT'
  var output1: Output = new Output(0)
  output1.addColor(ColorType.MATTE)
  output1.addColor(ColorType.GLOSSY)
  output1.addColor(ColorType.GLOSSY)
  output1.addColor(ColorType.GLOSSY)
  output1.addColor(ColorType.GLOSSY)
  
  var output2: Output = new Output(1)
  
  val VALID_OUTPUT: List[Output] = List(output1, output2)

}