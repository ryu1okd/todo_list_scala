package com.github.ryu1okd

import akka.http.scaladsl.server.{HttpApp, Route}

object WebServer extends HttpApp {

  override def routes: Route = {
    pathEndOrSingleSlash {
      get {
        complete("Hello ToDo List made by Scala.")
      }
    }
  }

}


