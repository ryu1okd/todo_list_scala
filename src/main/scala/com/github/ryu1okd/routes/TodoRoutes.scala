package com.github.ryu1okd.routes

import akka.http.scaladsl.server.Directives._
import com.github.ryu1okd.models.{Todo, TodoJsonProtocol, Todos}
import com.github.ryu1okd.services.TodoService

trait TodoRoutes extends TodoJsonProtocol {

  lazy val todoRoutes =
    pathPrefix("todos") {
      pathEndOrSingleSlash {
        get {
          parameter('q.?) { q =>
            complete(TodoService.find(q))
          }
        } ~ post {
          entity(as[Todo]) { todo =>
            complete(TodoService.add(todo))
          }
        }
      } ~ path(LongNumber) { id =>
        get {
          complete(TodoService.findById(Some(id)))
        }
      }
    }
}
