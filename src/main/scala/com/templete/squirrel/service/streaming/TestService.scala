package com.templete.squirrel.service.streaming

import akka.http.scaladsl.server.RequestContext
import com.redis.RedisClient
import com.templete.squirrel.common.DBsource
import com.templete.squirrel.dbEntity.Tables
import com.templete.squirrel.dbEntity.Tables._
import com.typesafe.scalalogging.Logger
import sample.Color
import slick.jdbc.MySQLProfile.api._

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TestService extends DBsource {
  private[this] val logger = Logger(this.getClass)

  def listsd: Future[Seq[Tables.StreamingdatasetRow]] = {
    logger.info("Streamingdataset listing...")
    db.run(Streamingdataset.result)
  }

  def getArray() = {
    logger.info("array listing...")
    Future(List(1, 2, 3, 4, 5, 6, 7))
  }

  def longNumber(c: Color, ctx: RequestContext) = {
    println(ctx)
    ctx.toString
  }

  def getRedisConf = {
    val r = new RedisClient(host = "104.224.158.43", port = 6379, secret = Some("redis"))
    val info = r.info.getOrElse(None).toString

    val sr1 = """(# )?([a-z,A-Z]*)?\r\n(\w*):(.*)""".r.replaceAllIn(info.toCharArray, """"$2":{"$3":"$4",},""")

    val sc2 ="""},"":{"""

    val sr2 = "{" + sr1.replace(sc2.toCharArray, "") + "}"

    val sc3 =""",(\r\n)?}""".r
    val sr3 = sc3.replaceAllIn(sr2, "}")
    val result = "\r\n".r.replaceAllIn(sr3, "")
    import org.json4s._
    import org.json4s.native.JsonMethods._
    parse(result)

  }
}
