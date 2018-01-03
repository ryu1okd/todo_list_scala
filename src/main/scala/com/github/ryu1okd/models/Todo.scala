package com.github.ryu1okd.models

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import org.joda.time.DateTime
import slick.jdbc.MySQLProfile.api._
import com.github.tototoshi.slick.MySQLJodaSupport._
import spray.json._
import com.github.ryu1okd.protocols.DateTimeJsonProtocol._

import scala.concurrent.Future

case class Todo (id: Long, title: String, body: String, status: Int, createdAt:Option[DateTime], updatedAt: Option[DateTime])

trait TodoJsonProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val todoFormat = jsonFormat6(Todo.apply)
}

class Todos(tag: Tag) extends Table[Todo] (tag, "todo") {
  def id = column[Long]("id", O.PrimaryKey)
  def title = column[String]("title")
  def body = column[String]("body")
  def status = column[Int]("status")
  def createdAt = column[Option[DateTime]]("created_at")
  def updatedAt = column[Option[DateTime]]("updated_at")
  def * = (id, title, body, status, createdAt, updatedAt) <> (Todo.tupled, Todo.unapply)

}

object Todos extends TableQuery(new Todos(_)){

  private val db = Database.forConfig("mysql")

  def findAll: Future[Seq[Todo]] = {
    db.run(this.result)
  }

  def find(id: Long): Future[Option[Todo]] = {
    db.run(this.filter(_.id === id).result.headOption)
  }

  def create(todo: Todo): Unit = {
    db.run(this += todo)
  }

  def update(todo: Todo): Unit = {
    db.run(this.filter(_.id === todo.id).update(todo))
  }
}
