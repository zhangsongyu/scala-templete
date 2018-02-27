name := "scala-templete"

version := "0.1"

scalaVersion := "2.11.11"

lazy val akkaVersion = "2.5.8"
lazy val akkaHttpVersion = "10.0.11"
lazy val slickVersion = "3.2.1"


resolvers += "aliyun repositories" at "http://maven.aliyun.com/nexus/content/repositories/central/"
resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.1"
)

libraryDependencies += "junit" % "junit" % "4.12"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % slickVersion,
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion
)
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % slickVersion
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion
)
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
)
libraryDependencies += "org.json4s" %% "json4s-native" % "3.5.2"
libraryDependencies += "de.heikoseeberger" %% "akka-http-json4s" % "1.18.1"
libraryDependencies += "de.heikoseeberger" %% "akka-http-jackson" % "1.18.1"


libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.0"
//-Dspark.master=local

libraryDependencies ++= Seq(
  "net.debasishg" %% "redisclient" % "3.4"
)

libraryDependencies += "io.spray" %% "spray-json" % "1.3.2"
libraryDependencies += "org.quartz-scheduler" % "quartz" % "2.3.0"

//kafka
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "1.0.0"
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "1.0.0"
