package cn.com.boco.mro

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by zhaogj on 27/03/2017.
  * 读取mro xml gz文件
  * spark-submit --master yarn-cluster --class cn.com.boco.mro.MroXmlGzRead mrospark_2.10-0.1.0.jar
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
    //mroXmlRdd.saveAsTextFile("/user/boco_zhaogj/output")

    var strTitle: String = null
    var strObject: String = null
    mroXmlRdd.map { x => {
      var strLine = x.trim()
      if (strLine.startsWith("<v>") && strLine.endsWith("</v>")) {
        strLine = strLine.substring(3)
        strLine = strLine.substring(0, strLine.length - 4)
        strTitle + strObject + strLine
      } else if (strLine.startsWith("<object ") && strLine.endsWith(">")) {
        strLine = strLine.substring(8)
        strLine = strLine.substring(0, strLine.length - 1)
        strObject = strLine
      }
      else if (strLine.startsWith("<smr>") && strLine.endsWith("</smr>")) {
        strLine = strLine.substring(5)
        strLine = strLine.substring(0, strLine.length - 6)
        strTitle = strLine
      }
    }
    }.saveAsTextFile("/user/boco_zhaogj/output")
    sc.stop()
  }
}
