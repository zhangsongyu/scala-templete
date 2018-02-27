import java.io.File
import java.util.Date
import javax.xml.crypto.Data

import org.junit.Test

trait FileAttrTime {
  var num: Long
  var unit: String

  override def toString = s"$num,$unit"

  def seconds = unit match {
    case "day" => num * 24 * 60 * 60
    case "hour" => num * 60 * 60
  }

  def toNum(timeStr: String) = timeStr.split(",")(0).toLong

  def toUnit(timeStr: String) = timeStr.split(",")(1)
}

case class ignoreOlderThan(var num: Long = 0l, var unit: String = "hour") extends FileAttrTime {
  def genObj(timeStr: String) = ignoreOlderThan(toNum(timeStr), toUnit(timeStr))
}

case class actionMinWait(var num: Long = 0l, var unit: String = "hour") extends FileAttrTime {
  def genObj(timeStr: String) = ignoreOlderThan(toNum(timeStr), toUnit(timeStr))
}

class SimpleTest {
  @Test
  def yuio: Unit = {
    ignoreOlderThan().genObj("2,hour")

    new Date(1513165459711L).getTime
  }

  @Test
  def ttt: Unit = {

    List(0) match {
      case Nil =>
        println("nil")
      case _ :: _ =>
        println("::")
    }

    val lli = List(List(0, 1, 2, 3))
    val res = lli.flatMap(_.map(_ + 1))
    println()


    val BookExtractorRE = """Book: title=([^,]+),\s+author=(.+)""".r //   val MagazineExtractorRE = """Magazine: title=([^,]+),\s+issue=(.+)""".r
    val catalog = Seq(
      "Book: title=Programming Scala Second Edition, author=Dean Wampler",
      "Magazine: title=The New Yorker, issue=January 2014",
      "Unknown: text=Who put this here??"
    )
    for (item <- catalog) {
      item match {
        case BookExtractorRE(title, author) =>
          println(s"""Book "$title", written by $author""")
        case entry => println(s"Unrecognized entry: $entry")
      }
    }
    BookExtractorRE
      .unapplySeq("Book: title=Programming Scala" +
        " Second Edition, author=Dean Wampler")
  }

  case class H(i: Int)

  val h = H(0).copy()
}
