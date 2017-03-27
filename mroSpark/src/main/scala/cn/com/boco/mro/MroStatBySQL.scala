package cn.com.boco.mro

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by zhaogj on 27/03/2017.
  * spark-submit --master yarn-cluster --class cn.com.boco.mro.MroStatBySQL mrospark_2.10-0.1.0.jar
  */
object MroStatBySQL {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("MroStatBySQL")
    //.setMaster("local[*]")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val df = sqlContext.read.json("/user/boco_zhaogj/output/mro_json")
    //    val df = sqlContext.read.json("/Users/zhaogj/tmp/part-10")
    df.registerTempTable("mro")
    //sqlContext.sql("select MmeCode, MmeGroupId, count(*) from mro group by MmeCode, MmeGroupId").collect().foreach(println)
    //    sqlContext.sql("select MmeCode, MmeGroupId, count(*) from mro group by MmeCode, MmeGroupId").repartition(1).map { x => {
    //      x
    //    }
    //    }.saveAsTextFile("/user/boco_zhaogj/output/stat")
    sqlContext.sql("select MmeCode, MmeGroupId, count(*) from mro group by MmeCode, MmeGroupId").write.json("/user/boco_zhaogj/output/json")
    //    df.groupBy("MmeCode","MmeGroupId").count().write.save("/user/boco_zhaogj/output/stat")
    sc.stop()
  }
}
