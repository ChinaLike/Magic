package com.kelee.frame.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by kelee on 2017-06-07.
 * AES加密
 */

public class AESEncryption {

    static final String AES = "AES/ECB/PKCS7Padding";

    /**
     * 加密字符串
     *
     * @param key     密钥
     * @param content 内容
     * @return
     */
    public static String encrypt(String key, String content) {
        try {
            byte[] keyStr = getKey(key);
            SecretKeySpec spec = new SecretKeySpec(keyStr, "AES");
            Cipher cipher = Cipher.getInstance(AES);//algorithmStr
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, spec);//   ʼ
            byte[] result = cipher.doFinal(byteContent);
            return Base64.encodeToString(result, Base64.DEFAULT); //
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密字符串
     *
     * @param key     密钥
     * @param content 内容
     * @return
     */
    public static String decrypt(String key, String content) {
        try {
            byte[] encryptedBytes = Base64.decode(content.getBytes(), Base64.DEFAULT);
            byte[] enCodeFormat = getKey(key);
            SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = null;
            cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] result = new byte[0];
            result = cipher.doFinal(encryptedBytes);
            return new String(result, "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 处理key不足位,不足16位不足16位（128bit），不足24位不足24位（192bit），不足32位不足32位（256bit）
     *
     * @param key
     * @return
     */
    private static byte[] getKey(String key) {
        int size = key.length();
        if (size >= 0 && size <=16){
            for (int i = size; i < 16; i++) {
                key = key +"\0";
            }
        }else if (size > 16 && size <=24){
            for (int i = size; i < 24; i++) {
                key = key +"\0";
            }
        }else if (size >24 && size <= 32){
            for (int i = size; i < 32; i++) {
                key = key +"\0";
            }
        }
        return key.getBytes();
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String byte2hex(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] hex2byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
