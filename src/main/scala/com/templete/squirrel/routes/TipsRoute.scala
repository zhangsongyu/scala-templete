package com.templete.squirrel.routes

import akka.http.scaladsl.server.{Directives, Route}
import com.templete.squirrel.common.JsonFormats
import com.typesafe.scalalogging.Logger
import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue}


import scala.concurrent.Future
import scala.util.Random

class TipsRoute extends JsonFormats with Directives  {
  private[this] val logger = Logger(this.getClass)

  import scala.concurrent.ExecutionContext.Implicits.global

  val route = {
    path("blocking") {
      get {
        complete {
          Future { // uses defaultDispatcher
            Thread.sleep(5000) // will block on default dispatcher,
            System.currentTimeMillis().toString // Starving the routing infrastructure
          }
          Future { // uses defaultDispatcher
            Thread.sleep(5000) // will block on default dispatcher,
            System.currentTimeMillis().toString // Starving the routing infrastructure
          }
          Future { // uses defaultDispatcher
            Thread.sleep(5000) // will block on default dispatcher,
            System.currentTimeMillis().toString // Starving the routing infrastructure
          }
          Future { // uses defaultDispatcher
            Thread.sleep(5000) // will block on default dispatcher,
            System.currentTimeMillis().toString // Starving the routing infrastructure
          }
        }
      }
    }
  }

}
