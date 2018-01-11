package com.templete.squirrel.routes

import akka.http.scaladsl.server.{Directives, Route}
import docs.CompileOnlySpec
import com.templete.squirrel.common.JsonFormats
import com.typesafe.scalalogging.Logger
import akka.NotUsed
import akka.stream.scaladsl.Source
import akka.http.scaladsl.model.sse.ServerSentEvent

import scala.concurrent.duration._
import java.time.LocalTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME
import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue}

import com.templete.squirrel.routes.SE.getCount
import com.templete.squirrel.util.RedisUtil

import scala.util.Random

class SseRoute extends JsonFormats with Directives with CompileOnlySpec {
  private[this] val logger = Logger(this.getClass)

  import akka.http.scaladsl.marshalling.sse.EventStreamMarshalling._

  val route = {
    path("events") {
      get {
        complete {
          Source
            .tick(2.seconds, 2.seconds, NotUsed)
            .map(_ => LocalTime.now())
            .map(time => ServerSentEvent(ISO_LOCAL_TIME.format(time)))
            .keepAlive(1.second, () => ServerSentEvent.heartbeat)
        }
      }
    } ~
      path("myevents") {
        get {
          complete {
            Source
              .tick(1.seconds, 1.seconds, NotUsed)
              .map(_ => (SE.getCount()))
              .map(d => ServerSentEvent(d.toString, "type", SE.getId))
          }
        }
      } ~
      path("getNumber") {
        get {
          complete {
            Source
              .tick(2.seconds, 2.seconds, NotUsed)
              .map(_ => if (SE.bl.isEmpty||SE.bl.peek()==null) "" else SE.bl.peek().toString)
              .map(d => d match {
                case ""=>ServerSentEvent.heartbeat
                case _=>ServerSentEvent(d)
              }
                )
          }
        }
      } ~
      path("count") {
        get {
          complete {
            getCount()
            "counting..."
          }
        }
      }
  }

}

object SE {
  var i = 0
  val bl = new ArrayBlockingQueue[Int](5)

  def getCount() = {
    i += 1
    if(!bl.offer(i)){}
    i
  }

  def addBl = {
    new Thread(
      new Runnable {
        override def run(): Unit = SE.bl.add(getCount())
      }).start()
  }

  def getId = {
    new Random().nextInt(6).toString
  }
}