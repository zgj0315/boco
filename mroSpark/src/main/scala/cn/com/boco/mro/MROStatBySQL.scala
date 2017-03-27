package cn.com.boco.mro

import org.apache.spark.sql.SparkSession

/**
  * Created by zhaogj on 24/03/2017.
  */
object MROStatBySQL {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      //.master("local[*]")
      .appName("MRO Stat")
      .getOrCreate()
    val df = spark.read.json("/Users/zhaogj/tmp/TD-LTE_MRO_HUAWEI_OMC_1112_29.json")
    //val df = spark.read.json("/Users/zhaogj/tmp/small.json")
    //        df.show()
    //df.printSchema()
    //df.select("MmeCode").show()
    //    df.groupBy("MmeGroupId").count().show()
    //df.groupBy("MmeCode", "MmeGroupId").count().show()

    df.createOrReplaceTempView("mro")
    //        val sqlDF = spark.sql("SELECT COUNT(*) FROM mro")
    val sqlDF = spark.sql("SELECT MmeCode, MR_LteNcEarfcn, COUNT(*) FROM mro GROUP BY MmeCode, MR_LteNcEarfcn")
    sqlDF.show()

    spark.stop()
  }
}
