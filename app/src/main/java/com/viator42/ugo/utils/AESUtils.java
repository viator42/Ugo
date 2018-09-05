package com.viator42.ugo.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Administrator on 2016/10/26.
 */

public class AESUtils {
    private final static String HEX = "0123456789ABCDEF";


    public static String decode(String key, String ciphertext) throws Exception {
        byte[] bs = parseHexStr2Byte(ciphertext);
        IvParameterSpec ivSpec = new IvParameterSpec(HEX.getBytes());
//        SecretKeySpec secretKeySpec = createKey(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
        return new String(c.doFinal(bs), "UTF-8");
    }

    public static String encode(String key, String cleartext) throws Exception {
//        SecretKeySpec secretKeySpec = createKey(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(HEX.getBytes());
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        String result = parseByte2HexStr(c.doFinal(cleartext.getBytes("UTF-8")));

        return result;
    }

    private static SecretKeySpec createKey(String key) {
        byte[] data = null;
        if (key == null) {
            key = "";
        }
        StringBuffer sb = new StringBuffer(16);
        sb.append(key);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }
        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, "AES");
    }

    private static String parseByte2HexStr(byte[] buf) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0XFF);
            if (hex.length() == 1) {
//				hex = '0' + hex;
                sb.append("0");
            }
            sb.append(hex);
        }
        return sb.toString().toUpperCase();
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr == null || hexStr.length() == 0) {
            return null;
        }
        int len = hexStr.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            // int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1),16);
            // int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 +2),16);
            // result[i] = (byte) (high * 16 + low);
            result[i] = (byte) (Integer.parseInt(hexStr.substring(2 * i, 2 * i + 2), 16) & 0XFF);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String key = "1111111111111111";
        String text = "1";

        String ret = encode(key, text);
        System.out.println("加密后:" + ret);

        String sb = decode(key, ret);
        System.out.println("解密后:" + sb);
    }

    /*
    public static String encrypt(byte[] seed, String cleartext) throws Exception {
        byte[] rawKey = getRawKey(seed);
        byte[] result = encrypt(rawKey, cleartext.getBytes());
        return toHex(result);
    }

    public static String decrypt(String seed, String encrypted) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }


    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        sr.setSeed(seed);
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }


    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCSPadding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }


    private static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    private static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    private static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }


    private static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private final static String HEX = "0123456789ABCDEF";

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
    */



}