import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.templete.squirrel.routes.{DefaultValueRoute, SseRoute, TestRoute, TipsRoute}
import akka.http.scaladsl.server.Directives._

import scala.io.StdIn


object ScalaRun {

  def main(args: Array[String]) {

    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val route = {
      new TestRoute().route ~ new SseRoute().route ~ new TipsRoute().route ~ new DefaultValueRoute().route
    }
    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ ⇒ system.terminate()) // and shutdown when done

  }
}