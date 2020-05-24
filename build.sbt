name := "MIChallenge"
scalaVersion := "2.13.2"

val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.1.11"
val akkaHttpJson = "de.heikoseeberger" %% "akka-http-play-json" % "1.29.1"
val akkaActor = "com.typesafe.akka" %% "akka-actor" % "2.5.26"
val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.5.26"
val redis = "net.debasishg" %% "redisclient" % "3.30"
val playJson = "com.typesafe.play" %% "play-json" % "2.8.1"
val cats = "org.typelevel" %% "cats-core" % "2.0.0"
val mockito = "org.mockito" % "mockito-all" % "1.10.19" % Test
val scalaMock = "org.scalamock" %% "scalamock" % "4.4.0" % Test
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8" % "test,it"
val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % "2.5.26" % "test,it"
val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % "10.1.11" % "test,it"
val scalatestRedis = "com.github.sebruck" %% "scalatest-embedded-redis" % "0.4.0"

libraryDependencies ++= Seq(
  akkaHttp,
  akkaStream,
  akkaActor,
  redis,
  playJson,
  akkaHttpJson,
  cats,
  mockito,
  scalaTest,
  akkaTestKit,
  akkaHttpTestKit,
  scalaMock,
  scalatestRedis
)
