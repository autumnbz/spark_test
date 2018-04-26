package wm.utils.ExtendOps

import org.apache.spark.sql.types.{LongType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row}

import scala.util.Random


object DataFrameOps {

  implicit class DataFrameOps(df: DataFrame) {
    def dfZipWithIndex(
                        offset: Int = 1,
                        colName: String = "index_id",
                        inFront: Boolean = true
                      ): DataFrame = {
      //增加index_id
      df.sqlContext.createDataFrame(
        df.rdd.zipWithIndex.map(ln =>
          Row.fromSeq(
            (if (inFront) Seq(ln._2 + offset) else Seq())
              ++ ln._1.toSeq ++
              (if (inFront) Seq() else Seq(ln._2 + offset))
          )
        ),
        StructType(
          (if (inFront) Array(StructField(colName, LongType, nullable = false)) else Array[StructField]())
            ++ df.schema.fields ++
            (if (inFront) Array[StructField]() else Array(StructField(colName, LongType, nullable = false)))
        )
      )
    }

    //df只要含有底层所有拥有的字段就可以insert
    def saveDFToHivePartition(tableName: String, date: String, dropPartitionFirst: Boolean = false): Unit = {
      import wm.utils.ExtendOps.SparkSessionOps._
      val spark = df.sparkSession
      val viewName = "spark_temp_view_" + Random.nextInt(1000)
      df.createOrReplaceTempView(viewName)
      //删除对应的partition应对重建, 只在手动重跑时才启用吧
      if (dropPartitionFirst) {
        spark.sql(s"""alter table $tableName drop if exists partition(dt='$date')""")
      }
      spark.sql(
        s"""
           |insert overwrite table $tableName partition(dt='$date')
           |select ${spark.getColumns(tableName).mkString(",")} from $viewName
      """.stripMargin('|'))
    }
  }

}
