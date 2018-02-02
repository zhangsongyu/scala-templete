package com.templete.squirrel.routes

import akka.http.scaladsl.server.Directives._
import com.templete.squirrel.common.JsonFormats
import com.typesafe.scalalogging.Logger



import com.templete.squirrel.util.Json4sSupport._

case class Dfv(num: Any = 5, unit: String = "hour")

class DefaultValueRoute extends JsonFormats {

  val any: Any = 5
  private[this] val logger = Logger(this.getClass)
  val route =
    pathPrefix("json") {
      path("dfv") {
        post {
          entity(as[Dfv]) { dfv =>
            complete(true)
          }
        }
      }
    }
}
