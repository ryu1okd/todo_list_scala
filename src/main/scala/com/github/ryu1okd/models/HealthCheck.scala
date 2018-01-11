package com.github.ryu1okd.models

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
  * for /health_check
  * @param status
  */
case class HealthCheck (status: String)

trait HealthCheckProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val healthCheckFormat = jsonFormat1(HealthCheck.apply)
}
