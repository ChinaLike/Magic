package com.kelee.frame.module;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.kelee.frame.core.AbsActivity;
import com.kelee.frame.core.BindingFactory;
import com.kelee.frame.module.inf.ModuleListener;
import com.kelee.frame.util.L;
import com.kelee.frame.util.StringUtil;

/**
 * Created by kelee on 2017-06-03.
 * 抽象的module，数据实现需继承该类
 */

public class AbsModule {
    public String TAG = "";
    private Context mContext;
    private ModuleListener mModuleListener;
    private OnCallback mCallback;
    private BindingFactory mBindingFactory;
    private Object mHost;


    public interface OnCallback {
        void onSuccess(int result, Object success);

        void onError(int result, Object error);
    }

    public AbsModule(Context context) {
        mContext = context;
        init();
    }

    /**
     * 初始化数据
     */
    private void init() {
        TAG = StringUtil.getClassName(this);
        mBindingFactory = BindingFactory.newInstance();
    }

    /**
     * 设置Module的监听
     * @param moduleListener
     */
    public void setModuleListener(ModuleListener moduleListener) {
        if (moduleListener == null)
            throw new NullPointerException("moduleListener不能为空");
        this.mModuleListener = moduleListener;
    }

    /**
     * 为Binding设置寄主
     * @param host
     */
    public void setHost(Object host) {
        this.mHost = host;
    }

    /**
     * 成功的回调
     */
    private void successCallback(int key, Object obj) {
        if (mCallback == null) {
            L.e(TAG, "OnCallback 为 null");
            return;
        }
        mCallback.onSuccess(key, obj);
    }

    /**
     * 失败的回调
     */
    public void errorCallback(int key, Object obj) {
        if (mCallback == null) {
            L.e(TAG, "OnCallback 为 null");
            return;
        }
        mCallback.onError(key, obj);
    }

    /**
     * 获取Context
     *
     * @return Context
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 设置Module回调
     *
     * @param callback Module 回调
     */
    public void setCallback(OnCallback callback) {
        mCallback = callback;
    }

    /**
     * 获取ViewDataBinding
     *
     * @param clazz ViewDataBinding实例
     */
    protected <VB extends ViewDataBinding> VB getBinding(Class<VB> clazz) {
        return mBindingFactory.getBinding(mHost, clazz);
    }

    /**
     * 统一的回调，如果已经设置了OnCallback，则使用OnCallback;
     * 否则将使用dataCallback，{@link AbsActivity#dataCallback(int, Object)}
     *
     * @param result 返回码
     * @param data 回调数据
     */
    protected void callback(final int result, final Object data) {
        if (mCallback != null) {
            successCallback(result, data);
            return;
        }
        mModuleListener.callback(result, data);
    }
}
