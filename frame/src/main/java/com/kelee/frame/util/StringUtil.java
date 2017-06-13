package com.kelee.frame.util;

/**
 * Created by kelee on 2017-06-01.
 * 字符串工具类
 */

public class StringUtil {

    /**
     * 获取指定对象的名称
     *
     * @param obj 对象
     * @return 对象名
     */
    public static String getClassName(Object obj) {
        String arrays[] = obj.getClass().getName().split("\\.");
        return arrays[arrays.length - 1];
    }

}
