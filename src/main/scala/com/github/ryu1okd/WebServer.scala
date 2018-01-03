package com.github.ryu1okd

import akka.http.scaladsl.server.{HttpApp, Route}
import com.github.ryu1okd.routes._

/**
  * server object
  */
object WebServer extends HttpApp with TodoRoutes {

  override def routes: Route = {
    pathEndOrSingleSlash {
      get {
        complete("Hello ToDo List made by Scala.")
      }
    } ~ todoRoutes
  }

}


