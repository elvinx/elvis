name := "elvis"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.4"

crossScalaVersions in ThisBuild := Seq("2.11.11", "2.12.4")

lazy val root =
  (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  guice,
  jdbc,
  "org.xerial" % "sqlite-jdbc" % "3.21.0",
  "net.ruippeixotog" %% "scala-scraper" % "2.1.0"
)

libraryDependencies += "org.playframework.anorm" %% "anorm" % "2.6.1"

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-language:postfixOps",
  "-language:implicitConversions"
)
