package com.templete.squirrel.routes

import akka.http.scaladsl.model.HttpEntity
import sample.{Color, TestBird, TestColor}
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import com.templete.squirrel.common.JsonFormats
import com.templete.squirrel.service.streaming.TestService
import com.typesafe.scalalogging.Logger
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._

class TestRoute extends JsonFormats {
  private[this] val logger = Logger(this.getClass)
  val route =
    pathPrefix("itoa") {
      path("hello") {
        get {
          { ctx => ctx.complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>")) }
        }
      } ~
        path("bird") {
          get {
            complete(TestBird.getBird)
          }
        } ~
        path("color") {
          get {
            complete(TestColor.getColor)
          }
        } ~
        path("list") {
          get {
            complete(new TestService().listsd)
          }
        } ~
        path("getarray") {
          get {
            complete(new TestService().getArray())
          }
        } ~
        path("longnumber") {
          entity(as[Color]) { en =>
            post {
              { ctx =>
                ctx.complete(new TestService().longNumber(en, ctx))
              }
            }
          }
        } ~
        path("getconf") {
          get {
            complete(new TestService().getRedisConf)
          }
        }
    }
}
