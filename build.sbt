name := "todo_list_scala"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"   % "10.1.0-RC1",
  "com.typesafe.akka" %% "akka-stream" % "2.5.8"
)
