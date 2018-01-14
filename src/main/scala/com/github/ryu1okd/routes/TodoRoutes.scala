package com.github.ryu1okd.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives._
import com.github.ryu1okd.models.{Todo, TodoJsonProtocol}
import com.github.ryu1okd.services.TodoService

/**
  * Routing of /todos
  */
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
            complete(StatusCodes.Created, TodoService.add(todo))
          }
        }
      } ~ path(LongNumber) { id =>
        get {
          onSuccess( TodoService.findById(id)) {
            case Some(todo) => complete(todo)
            case None => complete(StatusCodes.NotFound, "")
          }
        } ~ put {
          entity(as[Todo]) { todo =>
            onSuccess( TodoService.update(todo)) {
              case 0 => complete(StatusCodes.Created, TodoService.add(todo))
              case 1 => onSuccess(TodoService.findById(id)) {
                case Some(t) => complete(StatusCodes.OK, t)
                case None => complete(StatusCodes.InternalServerError, "Missing updated record.")
              }
            }
          }
        } ~ delete {
          onSuccess(TodoService.delete(id)) {
            if(_) complete(HttpEntity(ContentTypes.`application/json`, "{\"status\":\"ok\"}"))
            else complete(StatusCodes.NotFound, "")
          }
        }
      }
    }
}
