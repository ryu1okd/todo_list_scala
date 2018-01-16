package com.github.ryu1okd.services

import com.github.ryu1okd.models.{Todo, Todos}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TodoService {

  def find(query: Option[String], status: Option[String]): Future[Seq[Todo]] = {
    (query, status) match {
      case (Some(q), Some(status)) => Todos.findByTextAndStatus(q, status.toInt)
      case (None, Some(status)) => Todos.findByStatus(status.toInt)
      case (Some(q), None) => Todos.findByText(q)
      case (None, None) => Todos.findAll
    }
  }

  def findById(id: Long): Future[Option[Todo]] = {
    Todos.find(Some(id))
  }

  def add(todo: Todo): Future[Option[Todo]] = {
    for {
      newId <- Todos.create(todo)
      newTodo <- Todos.find(newId)
    } yield newTodo
  }

  def update(todo: Todo): Future[Int] = {
    Todos.update(todo)
  }

  def delete(id: Long): Future[Boolean] = {
    for {
      deletedRowsCount <- Todos.delete(Some(id))
    } yield if (deletedRowsCount > 0) true else false
  }
}
