package com.tianxing.magic.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.tianxing.magic.R;

/**
 * Created by kelee on 2017-06-08.
 * 自定义填充界面
 *
 */

public class TempView extends LinearLayout {
    // TODO: 2017/6/8/008 UI 给出界面后做

    private OnTempClickListener onTempClickListener;

    public TempView(Context context) {
        super(context, null);
    }

    public TempView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TempView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 填充界面点击监听
     */
    public interface OnTempClickListener {
        void onTempClick();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_temp, this);
    }

    /**
     * 点击监听
     *
     * @param onTempClickListener
     */
    public void setOnTempClickListener(OnTempClickListener onTempClickListener) {
        this.onTempClickListener = onTempClickListener;
    }
}
