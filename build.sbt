
import play.PlayJava

name := """Meeters"""

version := "1.0-SNAPSHOT"


lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.hibernate" % "hibernate-entitymanager" % "4.2.2.Final",
  "org.springframework" % "spring-web" % "4.0.2.RELEASE",
  "com.ganyo" % "gcm-server" % "1.0.2",
  "com.jolbox" % "bonecp" % "0.7.1.RELEASE",
  "com.jolbox" % "bonecp-provider" % "0.7.1.RELEASE",
  "org.reflections" % "reflections" % "0.9.8",
  javaCore
)


libraryDependencies += "commons-lang" % "commons-lang" % "2.3"

