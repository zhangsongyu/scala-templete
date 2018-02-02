package jsonTest

import com.templete.squirrel.common.JsonFormats
import org.json4s.native.JsonMethods.parse
import org.junit.Test
import org.scalatest.WordSpec
import sample.Color

class JsonTest extends WordSpec {

  import org.json4s._
  import org.json4s.native.JsonMethods._

  implicit val serialization = native.Serialization
  implicit val formats = DefaultFormats
  "json parse" in {
    val p = parse(""" { "numbers" : [1, 2, 3, 4],"fff":12.22E11 } """, useBigDecimalForDouble = true, useBigIntForLong = true)
    println(p)
  }

  "parse longNumber" in {


    val cc = serialization.read[Any]("""{"name": "String", "red": 12.44E12, "green": 12.22E11, "blue": 1234567543333323456}""")
    println(cc)
    case class TestJson()


  }

  //json4s
  def toJson(value: Any): String = {
    serialization.write(value.asInstanceOf[AnyRef])
  }

  def fromJson(json: String) = {
    serialization.read[AnyRef](json)
  }

  @Test
  def testJson4s :Unit= {
    val vb:Int =1
    val vo=List(1,2,3)
    val sb ="""1"""
    val so="""[1,2,3]"""

    println (toJson(vb))
    println(toJson(vo))

    val cc = serialization.read[Any]("""{"name": "String", "red": 12.44E12, "green": 12.22E11, "blue": 1234567543333323456}""")
    val p = parse("""1""", useBigDecimalForDouble = true, useBigIntForLong = true)

 //  val a= fromJson(sb)
    val aa=fromJson(so)
    println(aa)
  }

}


