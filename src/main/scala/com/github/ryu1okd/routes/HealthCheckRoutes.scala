package com.github.ryu1okd.routes

import akka.http.scaladsl.server.Directives._
import com.github.ryu1okd.models.{HealthCheck, HealthCheckProtocol}

trait HealthCheckRoutes extends HealthCheckProtocol {

  lazy val healthCheckRoutes =
    path("health_check") {
      get {
        complete(HealthCheck("ok"))
      }
    }
}
