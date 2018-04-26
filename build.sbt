organization := "com.jd"

name := "spark_test"

version := "1.0"

libraryDependencies ++= Seq(
  //spark
  "org.apache.spark" % "spark-core_2.11" % "2.1.0" ,
  "org.apache.spark" % "spark-sql_2.11" % "2.1.0" ,
  "org.apache.spark" % "spark-hive_2.11" % "2.1.0" ,
  "org.joda" % "joda-convert" % "1.8.1",

  //jd jimdb
  "com.jd.jim.cli" % "jim-cli-jedis" % "1.4.5-SNAPSHOT",
  "com.jd.jim.cli" % "jim-cli-api" % "1.4.5-SNAPSHOT"
)

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)


offline := true
