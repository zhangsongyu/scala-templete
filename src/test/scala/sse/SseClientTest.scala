package sse

import akka.NotUsed
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.sse.ServerSentEvent
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.scaladsl.Source
import org.junit.Test

class SseClient {
  @Test
  def getmsg: Unit = {
    import akka.http.scaladsl.unmarshalling.sse.EventStreamUnmarshalling._

    Http()
      .singleRequest(Get("http://localhost:8000/events"))
      .flatMap(Unmarshal(_).to[Source[ServerSentEvent, NotUsed]])
      .foreach(_.runForeach(println))
  }
}
