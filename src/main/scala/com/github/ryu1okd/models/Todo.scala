package com.github.ryu1okd.models

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import org.joda.time.DateTime
import slick.jdbc.MySQLProfile.api._
import com.github.tototoshi.slick.MySQLJodaSupport._
import spray.json._
import com.github.ryu1okd.protocols.DateTimeJsonProtocol._
import slick.ast.ColumnOption.{AutoInc, PrimaryKey}
import slick.sql.SqlProfile.ColumnOption.{NotNull, Nullable, SqlType}


import scala.concurrent.Future

case class Todo (id: Option[Long], title: String, body: String, status: Int, createdAt:Option[DateTime], updatedAt: Option[DateTime])

trait TodoJsonProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val printer = PrettyPrinter
  implicit val todoFormat = jsonFormat6(Todo.apply)
}

class Todos(tag: Tag) extends Table[Todo] (tag, "todo") {
  def id = column[Option[Long]]("id", PrimaryKey, AutoInc)
  def title = column[String]("title")
  def body = column[String]("body")
  def status = column[Int]("status")
  def createdAt = column[Option[DateTime]]("created_at", SqlType("DATETIME DEFAULT CURRENT_TIMESTAMP"))
  def updatedAt = column[Option[DateTime]]("updated_at", SqlType("DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"))
  def * = (id, title, body, status, createdAt, updatedAt) <> (Todo.tupled, Todo.unapply)

}

object Todos extends TableQuery(new Todos(_)){

  private val db = Database.forConfig("mysql")

  def findAll: Future[Seq[Todo]] = {
    db.run(this.result)
  }

  def find(id: Option[Long]): Future[Option[Todo]] = {
    db.run(this.filter(_.id === id).result.headOption)
  }

  def create(todo: Todo): Future[Option[Long]] = {
    db.run(( this returning this.map(_.id) ) += todo )
  }

  def update(todo: Todo): Unit = {
    db.run(this.filter(_.id === todo.id).update(todo))
  }

  def findByText(q: String): Future[Seq[Todo]] = {
    db.run(this.filter(p => (p.title like s"%${q}%") || (p.body like s"%${q}%")).result)
  }
}
