package studio.baxia.fo.util;

/**
 * Created by Pan on 2016/12/17.
 */
public class StringUtil {
    public static String byte2hex(byte[] buff)
    {
        if ((buff == null) || (buff.length <= 0)) {
            return "";
        }

        String tmpStr = null;
        StringBuilder hexStr = new StringBuilder();

        for (int n = 0; n < buff.length; n++) {
            tmpStr = Integer.toHexString(buff[n] & 0xFF);
            if (tmpStr.length() == 1) {
                hexStr.append("0");
            }
            hexStr.append(tmpStr);
        }
        return hexStr.toString().toUpperCase();
    }

    public static String byteToHex(byte[] b, boolean upper)
    {
        String result = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                result = new StringBuilder().append(result).append("0").append(stmp).toString();
            else {
                result = new StringBuilder().append(result).append(stmp).toString();
            }
        }
        if (upper) {
            return result.toUpperCase();
        }
        return result.toLowerCase();
    }
}
