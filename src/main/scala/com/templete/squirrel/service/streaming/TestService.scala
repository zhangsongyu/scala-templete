package com.templete.squirrel.service.streaming

import com.templete.squirrel.common.DBsource
import com.templete.squirrel.dbEntity.Tables._
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TestService extends DBsource {
  def listsd = {
    db.run(Streamingdataset.result)
  }

  def getArray()={
    Future(List(1,2,3,4,5,6,7))
  }
}
