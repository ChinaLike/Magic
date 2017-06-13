package com.kelee.frame.util;

import android.text.TextUtils;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by kelee on 2017-06-07.
 * MD5加密
 */

public class MD5 {

    /**
     * 加密字符串
     *
     * @param content 需要加密的字符串
     * @return  Hex格式加密字符串
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(String content) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = digest.digest(content.getBytes());
        return getString(bytes);
    }

    /**
     * 加密后Base64输出
     * @param content
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encryptBase64(String content)throws NoSuchAlgorithmException{
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] bytes = digest.digest(content.getBytes());
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    private static String getString(byte[] bytes) {
        String result = "";
        for (byte b : bytes) {
            String temp = Integer.toHexString(b & 0xff);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            result += temp;
        }
        return result;
    }

}
