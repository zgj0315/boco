import sbt.Keys._

lazy val commonSettings = Seq(
  organization := "cn.com.boco",
  name := "mroSpark",
  version := "0.1.0",
  scalaVersion := "2.11.8"
)

val spark_core = "org.apache.spark" % "spark-core_2.11" % "2.1.0"
val spark_sql = "org.apache.spark" % "spark-sql_2.11" % "2.1.0"
val spark_mllib = "org.apache.spark" % "spark-mllib_2.11" % "2.1.0"
val hadoop = "org.apache.hadoop" % "hadoop-client" % "2.7.3"

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies += spark_core,
    libraryDependencies += spark_sql,
    libraryDependencies += spark_mllib,
    libraryDependencies += hadoop
  )
