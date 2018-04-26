import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object Chapter_5_Basic_Structured_Operations {

  def main(args: Array[String]): Unit = {
    // in Scala
    val spark: SparkSession = SparkSession.builder.appName("spark").master("local").getOrCreate
    val df = spark.read.format("json")
      .load("d:/Users/cdchenhui1/Desktop/spark_test/data/flight-data/json/2015-summary.json")
      spark.sparkContext.setLogLevel("ERROR")

    // COMMAND ----------

    df.printSchema()
    println(spark.read.format("json").load("d:/Users/cdchenhui1/Desktop/spark_test/data/flight-data/json/2015-summary.json").schema)
    val myManualSchema = StructType(Array(
      StructField("DEST_COUNTRY_NAME", StringType, true),
      StructField("ORIGIN_COUNTRY_NAME", StringType, true),
      StructField("count", LongType, false,
        Metadata.fromJson("{\"hello\":\"world\"}"))
    ))
    val df1 = spark.read.format("json").schema(myManualSchema)
      .load("d:/Users/cdchenhui1/Desktop/spark_test/data/flight-data/json/2015-summary.json")
    df1.show()
    // COMMAND ----------
    import org.apache.spark.sql.functions.{col, column}
    col("someColumnName")
    column("someColumnName")
    println(df.groupBy(col("ORIGIN_COUNTRY_NAME")).sum("count").as("ct").show())

    (((col("someCol") + 5) * 200) - 6) < col("otherCol")
    // COMMAND ----------

    // in Scala

  }
}