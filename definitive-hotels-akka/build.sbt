import Dependencies._

ThisBuild / scalaVersion := "3.3.3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "net.nmoncho"
ThisBuild / organizationName := "nmoncho"

lazy val root = (project in file("."))
  .settings(
    name := "helenus-example-definitive-hotels-async",
    libraryDependencies ++= Seq(
      helenus,
      helenusAkka,
      akkaStream,
      alpakka,
      ossJavaDriver,
      cassandraUnit % Test,
      scalaTest     % Test,
      akkaTestKit   % Test,
      jna           % Test
    )
  )

addCommandAlias(
  "styleFix",
  "; scalafmtSbt; scalafmtAll"
)
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
