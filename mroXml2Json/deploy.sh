#!/bin/bash

mvn clean package -Dmaven.test.skip=true

scp target/mroXml2Json-1.0.jar.original boco_zhaogj@10.60.0.20:~/mroXml2Json/lib/mroXml2Json-1.0.jar
