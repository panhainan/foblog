package studio.baxia.fo.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.HashMap;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;

/**
 * jCryption support (www.jcryption.org) - RSA encryption
 * @author Michal Franc, FG Forrest (c) 2009
 *         18.10.2009 10:35:24
 */
public class JCryption {

    /**
     * Constructor
     */
    public JCryption() {
        java.security.Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Generates the Keypair with the given keyLength.
     *
     * @param keyLength length of key
     * @return KeyPair object
     * @throws RuntimeException if the RSA algorithm not supported
    */
    public KeyPair generateKeypair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(keyLength);
            return kpg.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("RSA algorithm not supported",e);
        }
    }

    /**
     * Decrypts a given string with the RSA keys
     * @param encrypted full encrypted text
     * @param keys RSA keys
     * @return decrypted text
     * @throws RuntimeException if the RSA algorithm not supported or decrypt operation failed
     */
    public String decrypt( String encrypted, KeyPair keys ) {
        Cipher dec;
        try {
            dec = Cipher.getInstance("RSA/NONE/NoPadding");
            dec.init(Cipher.DECRYPT_MODE, keys.getPrivate());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("RSA algorithm not supported",e);
        }
        String[] blocks = encrypted.split("\\s");
        StringBuffer result = new StringBuffer();
        try {
            for ( int i = blocks.length-1; i >= 0; i-- ) {
                byte[] data = hexStringToByteArray(blocks[i]);
                byte[] decryptedBlock = dec.doFinal(data);
                result.append( new String(decryptedBlock) );
            }
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Decrypt error",e);
        }
        return result.reverse().toString();
    }

    /**
     * Parse url string (Todo - better parsing algorithm)
     * @param url value to parse
     * @param encoding encoding value
     * @return Map with param name, value pairs
     */
    public static Map parse(String url,String encoding) {
        try {
            String urlToParse = URLDecoder.decode(url,encoding);
            String[] params = urlToParse.split("&");
            Map parsed = new HashMap();
            for (int i = 0; i<params.length; i++ )  {
                String[] p = params[i].split("=");
                String name = p[0];
                String value = (p.length==2)?p[1]:null;
                parsed.put(name, value);
            }
            return parsed;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown encoding.",e);
        }
    }

    /**
     * Return public RSA key modulus
     * @param keyPair RSA keys
     * @return modulus value as hex string
     */
    public static String getPublicKeyModulus( KeyPair keyPair ) {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return publicKey.getModulus().toString(16);
    }

    /**
     * Return public RSA key exponent
     * @param keyPair RSA keys
     * @return public exponent value as hex string
     */
    public static String getPublicKeyExponent( KeyPair keyPair ) {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return publicKey.getPublicExponent().toString(16);
    }

    /**
     * Max block size with given key length
     * @param keyLength length of key
     * @return numeber of digits
     */
    public static int getMaxDigits(int keyLength)   {
        return ((keyLength *2)/16)+3;
    }

    /**
     * Convert byte array to hex string
     * @param bytes input byte array
     * @return Hex string representation
     */
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (int i=0; i < bytes.length; i++) {
            result.append( Integer.toString( ( bytes[i] & 0xff ) + 0x100, 16).substring( 1 ) );
        }
        return result.toString();
    }

    /**
     * Convert hex string to byte array
     * @param data input string data
     * @return bytes
     */
    public static byte[] hexStringToByteArray(String data) {
        int k = 0;
        byte[] results = new byte[data.length() / 2];
        for (int i = 0; i < data.length();) {
            results[k] = (byte) (Character.digit(data.charAt(i++), 16) << 4);
            results[k] += (byte) (Character.digit(data.charAt(i++), 16));
            k++;
        }
        return results;
    }

}