package com.kelee.frame.temp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.kelee.frame.util.L;
import com.kelee.frame.util.StringUtil;

import butterknife.ButterKnife;

/**
 * Created by kelee on 2017-06-01.
 * 抽象的填充类，可用于初始化界面填充
 */

public abstract class AbsTempView extends LinearLayout implements ITempView {

    private static String TAG;
    protected int mType = ERROR;
    private OnTempBtClickListener mBtListener;

    public AbsTempView(Context context) {
        this(context, null);
    }

    public AbsTempView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(setLayoutId(), this);
        ButterKnife.bind(this, view);
        TAG = StringUtil.getClassName(this);
        init();
    }

    /**
     * 初始化界面的一些操作
     */
    protected abstract void init();

    /**
     * 需要填充的布局
     *
     * @return
     */
    protected abstract int setLayoutId();

    /**
     * 如果有按钮需要对按钮进行监听
     *
     * @param listener
     */
    public void setBtListener(@NonNull OnTempBtClickListener listener) {
        mBtListener = listener;
    }

    /**
     * 将按钮点击事件传递给TempView调用类
     *
     * @param type {@link ITempView}
     */
    protected void onTempBtClick(View view, int type) {
        if (mBtListener != null) {
            mBtListener.onBtTempClick(view, type);
        }
    }

    @Override
    public void setType(int type) {
        mType = type;
        if (type == LOADING) {
            onLoading();
            return;
        }
        if (type == ERROR) {
            onError();
        } else if (type == DATA_NULL) {
            onNull();
        } else {
            L.e(TAG, "填充类型设置错误");
        }
    }


}
