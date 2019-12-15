name := """scala-play-test"""

organization := "com.example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.13.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

lazy val versions = new {
  val play = "4.0.3"
  val guava = "27.1-jre"
}

libraryDependencies ++= Seq(
  guice,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % versions.play % Test,
  "com.google.guava" % "guava" % versions.guava
)

javaOptions in Universal ++= Seq(
  "-Dpidfile.path=/dev/null"
)
