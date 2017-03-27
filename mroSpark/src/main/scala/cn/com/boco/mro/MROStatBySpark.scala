package cn.com.boco.mro

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by zhaogj on 27/03/2017.
  */
object MROStatBySpark {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("MROStatBySpark")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    val mroJsonRdd = sc.textFile("/user/boco_zhaogj/mro_json/")
    mroJsonRdd.map { x => {
      val parts = x.split(",")
    }
    }
  }
}
