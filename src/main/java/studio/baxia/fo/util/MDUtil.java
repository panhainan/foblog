package studio.baxia.fo.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by FirePan on 2016/12/15.
 */
public class MDUtil {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String data = "美剧：冰与火之歌";
        System.out.println("明文：" + data);
        String encryptData = encodeMD5ToStr(data);
        System.out.println("密文：" + encryptData);
        byte[] encryptDataByte = encodeMD5ToByte(data);
        System.out.println(encryptDataByte);

        String userName = "test";
        String userPass = "test123";

    }

    public static byte[] encodeMD5ToByte(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] encryptData = messageDigest.digest(data.getBytes());
        return encryptData;
    }

    public static String encodeMD5ToStr(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        String encryptData = StringUtil.byte2hex(messageDigest.digest(data.getBytes(StandardCharsets.UTF_8)));
        return encryptData;
    }

    public static byte[] encodeMD2ToByte(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD2");
        byte[] encryptData = messageDigest.digest(data.getBytes(StandardCharsets.UTF_8));
        return encryptData;
    }

    public static String encodeMD2ToStr(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD2");
        String encryptData = StringUtil.byte2hex(messageDigest.digest(data.getBytes(StandardCharsets.UTF_8)));
        return encryptData;
    }

}
