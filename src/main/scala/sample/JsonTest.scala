package sample

import org.json4s.DefaultFormats
import org.json4s.JsonAST.{JInt, JObject}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class JsonTest {
}

object json4sTest extends App {

  import org.json4s._
  import org.json4s.native.JsonMethods._

  sealed abstract class JValue

  case object JNothing extends JValue // 'zero' for JValue
  case object JNull extends JValue

  case class JString(s: String) extends JValue

  case class JDouble(num: Double) extends JValue

  case class JDecimal(num: BigDecimal) extends JValue

  case class JInt(num: BigInt) extends JValue

  case class JBool(value: Boolean) extends JValue

  case class JObject(obj: List[JField]) extends JValue

  case class JArray(arr: List[JValue]) extends JValue

  type JField = (String, JValue)

  val pse = parse(""" { "numbers" : [1, 2, 3, 4] } """)
  val pse1 = parse("""{"name":"Toy","price":35.35}""", useBigDecimalForDouble = true)

  import org.json4s.JsonDSL._

  val jsonNum = List(1, 2, 3)
  val result = compact(render(jsonNum))

  println(result)
}

object JsonExample extends App {

  import org.json4s.JsonDSL._
  import org.json4s.native.JsonMethods._


  case class Winner(id: Long, numbers: List[Int])

  case class Lotto(id: Long, winningNumbers: List[Int], winners: List[Winner], drawDate: Option[java.util.Date])

  val winners = List(Winner(23, List(2, 45, 34, 23, 3, 5)), Winner(54, List(52, 3, 12, 11, 18, 22)))
  val lotto = Lotto(5, List(2, 45, 34, 23, 7, 5, 3), winners, None)

  val json =
    "lotto" ->
      ("lotto-id" -> lotto.id) ~
        ("winning-numbers" -> lotto.winningNumbers) ~
        ("draw-date" -> lotto.drawDate.map(_.toString)) ~
        ("winners" ->
          lotto.winners.map { w =>
            ("winner-id" -> w.id) ~
              ("numbers" -> w.numbers)
          })
  //  println(compact(render(json)))
  println(pretty(render(json)))

  implicit val formats = DefaultFormats

  case class Child(name: String, age: Int, birthdate: Option[java.util.Date])

  case class Address(street: String, city: String)

  case class Person(name: String, address: Address, children: List[Child])

  val jsoncap = parse(
    """
         { "name": "joe",
           "address": {
             "street": "Bulevard",
             "city": "Helsinki"
           },
           "children": [
             {
               "name": "Mary",
               "age": 5,
               "birthdate": "2004-09-04T18:06:22Z"
             },
             {
               "name": "Mazy",
               "age": 3
             }
           ]
         }
       """)
  val persion = jsoncap.extract[Person]

  JObject(("age", JInt(10)) :: Nil) transformField {
    case ("age", JInt(x)) =>
      ("age", JInt(x + 1))
  }

  println()

}
case class Color(name: String, red: Long, green: Long, blue: Long)

object TestBird {

  case class Bid(userId: String, bid: Int, color: Color)


  def getBird = {
    Bid("", 0, Color("CadetBlue", 95, 158, 160))
  }
}

object TestColor {
  def getColor = {
    Future(List(Color("CadetBlue", 95, 158, 160)))
  }
}