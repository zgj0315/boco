#!/bin/bash
sbt -java-home /Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home clean package

scp target/scala-2.10/mrospark_2.10-0.1.0.jar boco_zhaogj@10.60.0.20:~/spark/
# spark-submit --master yarn-cluster --class org.after90.spark.WordCount sbtprojecttest_2.10-0.1.0.jar