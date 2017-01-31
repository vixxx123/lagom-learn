organization in ThisBuild := "com.codelouders.learn.lagom"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

lazy val `lagom-learn` = (project in file("."))
  .aggregate(`lagom-learn-api`, `lagom-learn-impl`, `lagom-learn-stream-api`, `lagom-learn-stream-impl`)

lazy val `lagom-learn-api` = (project in file("lagom-learn-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-learn-impl` = (project in file("lagom-learn-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`lagom-learn-api`)

lazy val `lagom-learn-stream-api` = (project in file("lagom-learn-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-learn-stream-impl` = (project in file("lagom-learn-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`lagom-learn-stream-api`, `lagom-learn-api`)

