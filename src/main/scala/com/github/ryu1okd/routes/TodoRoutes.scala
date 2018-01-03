package com.github.ryu1okd.routes

import akka.http.scaladsl.server.Directives._
import com.github.ryu1okd.models.{TodoJsonProtocol, Todos}

trait TodoRoutes extends TodoJsonProtocol {

  lazy val todoRoutes =
    pathPrefix("todos") {
      pathEndOrSingleSlash {
        get {
          complete(Todos.findAll)
        }
      } ~ path(LongNumber) { id =>
        get {
          complete(Todos.find(id))
        }
      }
    }
}
