package com.kelee.frame.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by kelee on 2017-06-01.
 * Toast管理类
 */

public class T {
    /**
     * 是否显示Tost
     */
    public static boolean isShow = true;

    public static Toast toast;

    private T() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow) Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, int message) {
        if (isShow) Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow) Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, int message) {
        if (isShow) Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow) Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, int message, int duration) {
        if (isShow) Toast.makeText(context, message, duration).show();
    }

    /**
     * 显示最后一次点击计时
     * @param context
     * @param message
     */
    public static void showLastShort(Context context, CharSequence message){
        if (isShow){
            if (toast == null){
                toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
            }else {
                toast.setText(message);
            }
            toast.show();
        }
    }

    /**
     * 显示最后一次点击计时
     * @param context
     * @param message
     */
    public static void showLastCenterShort(Context context, CharSequence message){
        if (isShow){
            if (toast == null){
                toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
            }else {
                toast.setText(message);
            }
            toast.show();
        }
    }

}
