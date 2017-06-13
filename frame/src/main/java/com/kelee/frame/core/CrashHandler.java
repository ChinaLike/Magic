package com.kelee.frame.core;

import android.content.Context;
import android.os.Looper;

import com.kelee.frame.util.CalendarUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by kelee on 2017-06-01.
 * 异常捕获处理
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    private static final Object LOCK = new Object();
    private static volatile CrashHandler mCrashHandler = null;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;
    private String mServerHost, mPramKey;
    private String mExceptionFileName = CalendarUtils.getData() + "AbsExceptionFile.crash";

    private CrashHandler(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    /**
     * 获取CrashHandler实例，单例模式
     * @param context
     * @return
     */
    public static CrashHandler getInstance(Context context) {
        if (mCrashHandler == null) {
            synchronized (LOCK) {
                mCrashHandler = new CrashHandler(context);
            }
        }
        return mCrashHandler;
    }

    /**
     * 开启异常捕获
     * 需要网络权限，get请求，异常参数
     *
     * @param serverHost 服务器地址
     * @param key 数据传输键值
     */
    public void setServerHost(String serverHost, String key) {
        mServerHost = serverHost;
        mPramKey = key;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(e) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(t, e);
        } else {
            // Sleep一会后结束程序
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            //不要把线程都杀了，否则连日志都看不了
            //            android.os.Process.killProcess(android.os.Process.myPid());
            //如果把这句话注释掉，有异常都不会退出
            System.exit(10);
        }
    }

    /**
     * 处理捕获到的异常
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //在这里处理崩溃逻辑,将不再显示FC对话框
        new Thread() {
            @Override public void run() {
                Looper.prepare();
                //T.showLong(mContext, "很抱歉，程序出现异常，即将退出");
                Looper.loop();
            }
        }.start();
        sendExceptionInfo(ex);
        return true;
    }

    /**
     * 发送异常数据给服务器
     */
    private void sendExceptionInfo(final Throwable ex) {
        //File file = new File(mContext.getCacheDir().getPath() + "/crash/" + mExceptionFileName);
        //if (!file.exists()) {
        //  FileUtil.createFile(file.getPath());
        //}
        // TODO: 2017/6/1/001 这里处理异常上传
//        ExceptionInfo info = new ExceptionInfo();
//        info.time = CalendarUtils.getNowDataTime();
//        info.versionCode = AndroidUtils.getVersionCode(mContext);
//        info.versionName = AndroidUtils.getVersionName(mContext);
//        info.systemVersionCode = Build.VERSION.SDK_INT;
//        info.phoneModel = Build.MODEL;
//        info.exceptionMsg = FL.getExceptionString(ex);
//        //writeExceptionToFile(info.exceptionMsg, file);
//        FL.e(TAG, FL.getExceptionString(ex));
//        if (AndroidUtils.checkPermission(mContext, Manifest.permission.INTERNET)
//                && AndroidUtils.checkPermission(mContext, Manifest.permission.ACCESS_NETWORK_STATE)) {
//            if (NetUtils.isConnected(mContext) && !TextUtils.isEmpty(mServerHost) && !TextUtils.isEmpty(
//                    mPramKey)) {
//                HttpUtil util = HttpUtil.getInstance(mContext);
//                String objStr = new Gson().toJson(info);
//                Map<String, String> params = new WeakHashMap<>();
//                params.put(mPramKey, objStr);
//                util.get(mServerHost, params, new HttpUtil.AbsResponse());
//                //util.uploadFile(mServerHost, file.getPath(), mPramKey, HttpUtil.CONTENT_TYPE_TEXT, null, new HttpUtil.AbsResponse());
//            }
//        } else {
//            L.e(TAG,
//                    "请在manifest文件定义android.permission.INTERNET和android.permission.ACCESS_NETWORK_STATE权限");
//        }
    }

    /**
     * 将异常日志写入文件
     */
    private void writeExceptionToFile(String message, File crashFile) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(CalendarUtils.getNowDataTime());
        stringBuffer.append("\n");
        stringBuffer.append(message);
        stringBuffer.append("\n");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(crashFile, true));
            writer.append(stringBuffer);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private class ExceptionInfo {
        int versionCode;
        String versionName;
        int systemVersionCode;
        String exceptionMsg;
        String phoneModel;
        String time;
    }
}