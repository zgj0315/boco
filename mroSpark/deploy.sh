#!/bin/bash
sbt -java-home /Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home package

# spark-submit --master yarn-cluster --class org.after90.spark.WordCount sbtprojecttest_2.10-0.1.0.jar