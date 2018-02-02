package jsonTest

import org.junit.Test
import org.json4s._
import org.json4s.jackson.JsonMethods._
import sample.JsonExample.Child

class BaseTypeTest {
  @Test
  def btt: Unit = {
   import org.json4s._
   import org.json4s.native.Serialization
   import org.json4s.native.Serialization.{read, write}
   implicit val formats = Serialization.formats(NoTypeHints)
   val ser = write(Child("Mary", 5, None))

    read[Child](ser)

  }
}
