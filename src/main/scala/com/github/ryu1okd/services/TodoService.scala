package com.github.ryu1okd.services

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
      newId <- Todos.create(todo)
      newTodo <- Todos.find(newId)
    } yield newTodo
  }

  def update(todo: Todo): Future[Option[Todo]] = {
    for {
      updatedRowsCount <- Todos.update(todo)
      updatedTodo <- Todos.find(todo.id) if updatedRowsCount > 0
    } yield updatedTodo
  }

  def delete(id: Long): Future[Boolean] = {
    for {
      deletedRowsCount <- Todos.delete(Some(id))
    } yield if (deletedRowsCount > 0) true else false
  }
}
