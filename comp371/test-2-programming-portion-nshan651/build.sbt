name := "nanol-parser-scala"

version := "0.1"

scalaVersion := "3.2.1"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Yexplicit-nulls", "-Ysafe-init", "-language:strictEquality")

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1",
  "org.scalatest"          %% "scalatest"                % "3.2.14" % Test
)

enablePlugins(JavaAppPackaging)
