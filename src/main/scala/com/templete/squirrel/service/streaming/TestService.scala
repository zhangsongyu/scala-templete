package com.templete.squirrel.service.streaming

import akka.http.scaladsl.server.RequestContext
import com.templete.squirrel.common.DBsource
import com.templete.squirrel.dbEntity.Tables
import com.templete.squirrel.dbEntity.Tables._
import com.typesafe.scalalogging.Logger
import sample.Color
import slick.jdbc.MySQLProfile.api._

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

  def longNumber(c: Color,ctx:RequestContext) = {
    println(ctx)
    ctx.toString
  }
}
