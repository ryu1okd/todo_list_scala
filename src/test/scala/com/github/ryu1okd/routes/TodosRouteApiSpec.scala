package com.github.ryu1okd.routes

import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.marshalling.Marshal
import com.github.ryu1okd.models.{Todo, TodoJsonProtocol}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}
import scala.sys.process._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

class TodosRouteApiSpec extends WordSpec with Matchers with ScalatestRouteTest with BeforeAndAfterAll with TodoJsonProtocol with TodoRoutes {

  "TodosRoutes " should {
    "when colling Get '/todos' should return Todo json list " in {
      Get("/todos") ~> todoRoutes ~> check {
        responseAs[Seq[Todo]] should have length 2
      }
    }

    "when colling Get '/todos?q=2' should return length 1" in {
      Get("/todos?q=2") ~> todoRoutes ~> check {
        responseAs[Seq[Todo]] should have length 1
      }
    }

    "when colling Get '/todos?status=0' should return status = 0" in {
      Get("/todos?status=0") ~> todoRoutes ~> check {
        responseAs[Seq[Todo]].map(_.status) should contain only(0)
      }
    }

    "Get '/todos?q=TITLE&status=1' should return 1 item " in {
      Get("/todos?q=TITLE&status=1") ~> todoRoutes ~> check {
        responseAs[Seq[Todo]] should have length 1
        responseAs[Seq[Todo]].map(t => t.title + " " + t.body).foreach( _ should include("TITLE"))
        responseAs[Seq[Todo]].map(_.status) should contain only(1)
      }
    }

    "when colling Post '/todos' should create Todo and retrun created Todo" in {
      val todoJson =
        """
          |{
          |  "title":"TEST TITLE",
          |  "body":"TEST BODY FROM TodosRouteApiSpec",
          |  "status":0
          |}
        """.stripMargin
      Post("/todos").withEntity(ContentTypes.`application/json`, todoJson) ~> todoRoutes ~> check {
        status should equal(StatusCodes.Created)
        responseAs[Todo].title should equal("TEST TITLE")
        responseAs[Todo].body should equal("TEST BODY FROM TodosRouteApiSpec")
      }
    }

    "when colling Get '/todos/1' should return Todo of id=1 " in {
      Get("/todos/1") ~> todoRoutes ~> check {
        responseAs[Todo].id should equal(Some(1))
        responseAs[Todo].title should equal("INIT_TITLE1")
        responseAs[Todo].body should equal("INIT_BODY1")
      }
    }

    "when colling Put '/todos/2' should update Todo " in {
      val todoJson =
        """
          |{
          |  "id":2,
          |  "title":"UPDATED TITLE",
          |  "body":"UPDATED BODY FROM TodosRouteApiSpec",
          |  "status":1
          |}
        """.stripMargin
      Put("/todos/2").withEntity(ContentTypes.`application/json`, todoJson) ~> todoRoutes ~> check {
        status should equal(StatusCodes.OK)
        responseAs[Todo].title should equal("UPDATED TITLE")
        responseAs[Todo].body should equal("UPDATED BODY FROM TodosRouteApiSpec")
        responseAs[Todo].status should equal(1)
      }
    }

    "when colling Put '/todos/4' should create Todo " in {
      val todoJson =
        """
          |{
          |  "id":4,
          |  "title":"Created On Put",
          |  "body":" BODY FROM TodosRouteApiSpec",
          |  "status":0
          |}
        """.stripMargin
      Put("/todos/4").withEntity(ContentTypes.`application/json`, todoJson) ~> todoRoutes ~> check {
        status should equal(StatusCodes.Created)
      }
    }

    "when colling Delete '/todos/1' delete todo " in {
      Delete("/todos/1") ~> todoRoutes ~> check {
        status should equal(StatusCodes.OK)
        responseAs[String] shouldEqual "{\"status\":\"ok\"}"
      }
    }

    "when colling Get '/todos/1' 404 NotFound " in {
      Get("/todos/1") ~> todoRoutes ~> check {
        status should equal(StatusCodes.NotFound)
      }
    }
  }
}
