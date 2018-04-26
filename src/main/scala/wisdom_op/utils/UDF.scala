package wm.utils

import org.apache.spark.sql.SparkSession


case object UDF {

  def registerAllFunc(spark: SparkSession): Unit = {
    //spark.udf.register("stripUrl", stripUrl _)
  }
}
