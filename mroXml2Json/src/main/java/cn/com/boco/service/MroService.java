package cn.com.boco.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by zhaogj on 24/03/2017.
 */
@Service
@Slf4j
public class MroService {

    public void doJob() {
        File fileXmlPath = new File("/home2/data4zhaogj/mro_xml/");
        if (fileXmlPath.isDirectory()) {
            File[] fileXmlList = fileXmlPath.listFiles();
            long lXmlFileSize = 0;
            long lJsonFileSize = 0;
            long lStart = System.currentTimeMillis();
            for (File fileXml : fileXmlList) {
                if (fileXml.getName().endsWith(".gz")) {
                    xml2Json(fileXml, "/home2/data4zhaogj/mro_json/" + fileXml.getName() + ".json");
                    lXmlFileSize += fileXml.length();
                    lJsonFileSize += (new File("/home2/data4zhaogj/mro_json/" + fileXml.getName() + ".json")).length();
                }
            }
            long lTime = (System.currentTimeMillis() - lStart) / 1000;
            log.info("转换时间开销:{}秒", lTime);
            log.info("转换速度xml:{}M/s, json:{}M/s", (lXmlFileSize / 1024 / 1024) / lTime, (lJsonFileSize / 1024 / 1024) / lTime);
        }
    }

    public void xml2Json(File fileXml, String strJsonPath) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            if (fileXml.getName().endsWith(".gz")) {
                br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(fileXml))));
            } else {
                br = new BufferedReader(new FileReader(fileXml));
            }
            bw = new BufferedWriter(new FileWriter(strJsonPath));
            String strLine = null;
            String[] astrTitle = null;
            String strObject = null;
            String[] astrValue = null;
            while ((strLine = br.readLine()) != null) {
                StringBuffer sbOutput = null;
                strLine = strLine.trim();
                //这个条件命中率最高，放在最外层
                if (strLine.startsWith("<v>") && strLine.endsWith("</v>")) {
                    strLine = strLine.substring(3);
                    strLine = strLine.substring(0, strLine.length() - 4);
                    astrValue = strLine.split(" ");
                    sbOutput = new StringBuffer();
                    sbOutput.append(strObject);
                    for (int i = 0; i < astrTitle.length; i++) {
                        sbOutput.append(", \"");
                        sbOutput.append(astrTitle[i]);
                        sbOutput.append("\":\"");
                        sbOutput.append(astrValue[i]);
                        sbOutput.append("\"");
                    }
                    sbOutput.append("}\n");
                    bw.write(sbOutput.toString());
                } else {
                    if (strLine.startsWith("<object ") && strLine.endsWith(">")) {
                        strLine = strLine.substring(8);
                        strLine = strLine.substring(0, strLine.length() - 1);
                        strObject = "{\"" + strLine.replaceAll("\" ", "\", \"").replaceAll("=\"", "\":\"");
                    } else {
                        if (strLine.startsWith("<smr>") && strLine.endsWith("</smr>")) {
                            strLine = strLine.substring(5);
                            strLine = strLine.substring(0, strLine.length() - 6);
                            strLine = strLine.replaceAll("\\.", "\\_");
                            astrTitle = strLine.split(" ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    log.error("", e);
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }
    }

    //测试gz格式xml文件读取速度
    public void readXmlSpeet(File fileXml, String strJsonPath) {
        BufferedReader br = null;
        try {
            if (fileXml.getName().endsWith(".gz")) {
                br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(fileXml))));
            } else {
                br = new BufferedReader(new FileReader(fileXml));
            }
            String strLine = null;
            String[] astrTitle = null;
            String strObject = null;
            String[] astrValue = null;
            while ((strLine = br.readLine()) != null) {

            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }
    }

    //测试gz格式xml文件读取和内容转换的速度
    public void readXmlAndConvertSpeet(File fileXml, String strJsonPath) {
        BufferedReader br = null;
        try {
            if (fileXml.getName().endsWith(".gz")) {
                br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(fileXml))));
            } else {
                br = new BufferedReader(new FileReader(fileXml));
            }
            String strLine = null;
            String[] astrTitle = null;
            String strObject = null;
            String[] astrValue = null;
            while ((strLine = br.readLine()) != null) {
                StringBuffer sbOutput = null;
                strLine = strLine.trim();
                //这个条件命中率最高，放在最外层
                if (strLine.startsWith("<v>") && strLine.endsWith("</v>")) {
//                    strLine = strLine.substring(3);
//                    strLine = strLine.substring(0, strLine.length() - 4);
//                    astrValue = strLine.split(" ");
//                    sbOutput = new StringBuffer();
//                    sbOutput.append(strObject);
//                    for (int i = 0; i < astrTitle.length; i++) {
//                        sbOutput.append(", \"");
//                        sbOutput.append(astrTitle[i]);
//                        sbOutput.append("\":\"");
//                        sbOutput.append(astrValue[i]);
//                        sbOutput.append("\"");
//                    }
//                    sbOutput.append("}\n");
                } else {
                    if (strLine.startsWith("<object ") && strLine.endsWith(">")) {
//                        strLine = strLine.substring(8);
//                        strLine = strLine.substring(0, strLine.length() - 1);
//                        strObject = "{\"" + strLine.replaceAll("\" ", "\", \"").replaceAll("=\"", "\":\"");
                    } else {
                        if (strLine.startsWith("<smr>") && strLine.endsWith("</smr>")) {
//                            strLine = strLine.substring(5);
//                            strLine = strLine.substring(0, strLine.length() - 6);
//                            strLine = strLine.replaceAll("\\.", "\\_");
//                            astrTitle = strLine.split(" ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }
    }

}
