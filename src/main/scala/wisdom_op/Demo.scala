package wm

import java.util.Date
import org.joda.time.{DateTime, LocalDate}

object Demo {
  def main(args: Array[String]): Unit = {
    //val spark = SparkSession.builder()
    //  .appName("HLL Demo")
    //  .enableHiveSupport()
    //  .getOrCreate()
    //
    //
    //import spark.implicits._
    //import functions._
    //import org.apache.spark.sql.functions._
    //
    //
    //val df = List((1 to 1000).zip(Stream from 'a'): _*).toDF("c1", "c2")
    //
    //df.repartition('c1)

    val dt = new DateTime(LocalDate.fromDateFields(new Date).toDate).minusDays(10)

    println(dt)
  }
}
