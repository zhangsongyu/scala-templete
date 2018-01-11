/*
 * Copyright (C) 2009-2017 Lightbend Inc. <http://www.lightbend.com>
 */

package docs.http.scaladsl

import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.{Directives, Route}
import docs.CompileOnlySpec
import docs.http.scaladsl.server.RoutingSpec

class RouteSealExampleSpec extends RoutingSpec with Directives with CompileOnlySpec {

  "seal route example" in compileOnlySpec {
    //#route-seal-example
    val route = respondWithHeader(RawHeader("special-header", "you always have this even in 404")) {
      Route.seal(
        get {
          pathSingleSlash {
            complete {
              "Captain on the bridge!"
            }
          }
        }
      )
    }
    //#route-seal-example
  }

}
