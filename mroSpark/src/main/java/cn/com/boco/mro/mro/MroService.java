package cn.com.boco.mro.mro;

/**
 * Created by zhaogj on 27/03/2017.
 */
public class MroService {

    public String xml2json(String strLine) {
        String[] astrTitle = null;
        String[] astrValue = null;
        String strObject = null;

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
            return sbOutput.toString();
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
        return "";
    }
}
