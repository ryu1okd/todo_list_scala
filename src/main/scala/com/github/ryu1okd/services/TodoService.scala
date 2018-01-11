package com.github.ryu1okd.services

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import com.github.ryu1okd.models.{Todo, Todos}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TodoService {

  def find(query: Option[String]): Future[Seq[Todo]] = {
    query match {
      case Some(q) => Todos.findByText(q)
      case None => Todos.findAll
    }
  }

  def findById(id: Option[Long]): Future[Option[Todo]] = {
    Todos.find(id)
  }

  def add(todo: Todo): Future[Option[Todo]] = {
    for {
      maybeNewId <- Todos.create(todo)
      newTodo <- Todos.find(maybeNewId)
    } yield newTodo
  }
}
