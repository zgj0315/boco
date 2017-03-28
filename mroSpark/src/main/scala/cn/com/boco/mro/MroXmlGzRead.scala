package cn.com.boco.mro

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by zhaogj on 27/03/2017.
  * 读取mro xml gz文件
  * spark-submit --master yarn-cluster --class cn.com.boco.mro.MroXmlGzRead sbtprojecttest_2.10-0.1.0.jar
  */
object MroXmlGzRead {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("MroXmlGzReadSpark")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder
      .master("local[*]")
      .appName("MroXmlGzReadSession")
      .getOrCreate()

    val mroXmlGz = sc.textFile("/Users/zhaogj/tmp/TD-LTE_MRO_HUAWEI_OMC_1112_1.xml.gz")

    var strObject: String = null
    var astrTitle: Array[String] = null
    val mroJson = mroXmlGz.map { x => {
      var strLine = x.trim()
      if (strLine.startsWith("<v>") && strLine.endsWith("</v>")) {
        strLine = strLine.substring(3)
        strLine = strLine.substring(0, strLine.length - 4)
        val astrValue = strLine.split(" ")
        val sbOutput: StringBuffer = new StringBuffer()
        sbOutput.append(strObject)
        for (i <- 0 until astrTitle.length) {
          sbOutput.append(", \"")
          sbOutput.append(astrTitle(i))
          sbOutput.append("\":\"")
          sbOutput.append(astrValue(i))
          sbOutput.append("\"")
        }
        sbOutput.append("}\n")
        sbOutput.toString()
      } else if (strLine.startsWith("<object ") && strLine.endsWith(">")) {
        strLine = strLine.substring(8)
        strLine = strLine.substring(0, strLine.length - 1)
        strObject = "{\"" + strLine.replaceAll("\" ", "\", \"").replaceAll("=\"", "\":\"")
        ""
      } else if (strLine.startsWith("<smr>") && strLine.endsWith("</smr>")) {
        strLine = strLine.substring(5)
        strLine = strLine.substring(0, strLine.length - 6)
        strLine = strLine.replaceAll("\\.", "\\_")
        astrTitle = strLine.split(" ")
        ""
      } else {
        ""
      }
    }
    }

    val df = spark.read.json(mroJson)
    df.createOrReplaceTempView("mro")
    spark.sql("select MmeCode, MmeGroupId, count(*) from mro group by MmeCode, MmeGroupId").show()
    sc.stop()
  }
}
