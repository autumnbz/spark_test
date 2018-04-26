package wm.utils.ExtendOps

import org.apache.spark.sql.SparkSession

/**
  * Created by liguang on 17/4/18.
  */
object SparkSessionOps {

  //假设dt为分区列
  implicit class SparkSessionOps(spark: SparkSession) {
    def getColumns(tableName: String): Array[String] = {
      import spark.implicits._
      spark.sql(s"""desc $tableName""")
        .map(_.getAs[String]("col_name"))
        .collect
        .filter(column => !column.startsWith("#"))
        .filter(column => column != "dt")
    }
  }

}
