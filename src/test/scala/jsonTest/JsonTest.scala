package jsonTest

import com.templete.squirrel.common.JsonFormats
import org.json4s.native.JsonMethods.parse
import org.scalatest.WordSpec
import sample.Color

class JsonTest extends WordSpec {

  import org.json4s._
  import org.json4s.native.JsonMethods._

  "json parse" in {
    val p = parse(""" { "numbers" : [1, 2, 3, 4],"fff":12.22E11 } """, useBigDecimalForDouble = true, useBigIntForLong = true)
    println(p)
  }

  "parse longNumber" in {
    implicit val serialization = native.Serialization
    implicit val formats = DefaultFormats


    val cc = serialization.read[Any]("""{"name": "String", "red": 12.44E12, "green": 12.22E11, "blue": 1234567543333323456}""")
    println(cc)
  }
}


