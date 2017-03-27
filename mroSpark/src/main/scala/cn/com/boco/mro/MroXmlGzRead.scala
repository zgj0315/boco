package cn.com.boco.mro

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by zhaogj on 27/03/2017.
  * 读取mro xml gz文件
  * spark-submit --master yarn-cluster --class cn.com.boco.mro.MroXmlGzRead sbtprojecttest_2.10-0.1.0.jar
  */
object MroXmlGzRead {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("MroXmlGzRead")
    //.setMaster("local[*]")
    val sc = new SparkContext(conf)
    //    val mroXmlRdd = sc.textFile("/Users/zhaogj/tmp/TD-LTE_MRO_HUAWEI_OMC_1112_1.xml.gz")
    //    mroXmlRdd.saveAsTextFile("/Users/zhaogj/tmp/output")
    val mroXmlRdd = sc.textFile("/user/boco_zhaogj/mro_xml/")
    mroXmlRdd.saveAsTextFile("/user/boco_zhaogj/output")
    sc.stop()
  }
}
