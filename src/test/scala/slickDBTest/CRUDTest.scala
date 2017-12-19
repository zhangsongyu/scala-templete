package slickDBTest

import java.lang.Thread

import com.templete.squirrel.dbEntity.Tables
import com.templete.squirrel.dbEntity.Tables._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

class CRUDTest {

}

trait DBsource {
  val db = Database.forConfig("dbmysql")
}

object ListData extends App with DBsource {
  def t = db.run(Streamingdataset.result)

 val r= t.onComplete {
   case Success(v)=> v.foreach(f=>println(f.datasetid))
   case Failure(ex)=>
  }
Thread.sleep(10000)
}

