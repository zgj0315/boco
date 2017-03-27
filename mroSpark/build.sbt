import sbt.Keys._

lazy val commonSettings = Seq(
  organization := "cn.com.boco",
  name := "mroSpark",
  version := "0.1.0",
  scalaVersion := "2.10.6"
)

resolvers ++= Seq(
  "cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/"
)

val spark = "org.apache.spark" % "spark-core_2.10" % "1.6.0-cdh5.7.0"
val hdfs = "org.apache.hadoop" % "hadoop-hdfs" % "2.6.0-cdh5.7.0" excludeAll (ExclusionRule(organization = "javax.servlet"))
val spark_sql = "org.apache.spark" % "spark-sql_2.10" % "1.6.0-cdh5.7.0" excludeAll (ExclusionRule(organization = "javax.servlet"))

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies += spark,
    libraryDependencies += hdfs,
    libraryDependencies += spark_sql
  )