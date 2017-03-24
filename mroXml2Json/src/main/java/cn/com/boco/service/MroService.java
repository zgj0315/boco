package cn.com.boco.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by zhaogj on 24/03/2017.
 */
@Service
@Slf4j
public class MroService {
    public void xml2Json(String strXml, String strJson) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File(strXml)));
            bw = new BufferedWriter(new FileWriter(strJson));
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
                        sbOutput.append(" \"");
                        sbOutput.append(astrTitle[i]);
                        sbOutput.append("\"=\"");
                        sbOutput.append(astrValue[i]);
                        sbOutput.append("\"");
                    }
                    sbOutput.append("\n");
                    bw.write(sbOutput.toString());
//                    log.info("output:{}", sbOutput.toString());
                } else {
                    if (strLine.startsWith("<object ") && strLine.endsWith(">")) {
                        strLine = strLine.substring(8);
                        strLine = strLine.substring(0, strLine.length() - 1);
                        strObject = "\"" + strLine.replaceAll("\" ", "\" \"").replaceAll("=\"", "\"=\"");
                    } else {
                        if (strLine.startsWith("<smr>") && strLine.endsWith("</smr>")) {
                            strLine = strLine.substring(5);
                            strLine = strLine.substring(0, strLine.length() - 6);
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
}
