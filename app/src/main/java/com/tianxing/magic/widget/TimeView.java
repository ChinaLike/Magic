package com.tianxing.magic.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.kelee.frame.util.DensityUtils;
import com.kelee.frame.util.L;
import com.tianxing.magic.R;

/**
 * Created by kelee on 2017-06-12.
 * 时间选择底部底部进度显示
 */

public class TimeView extends View {
    /**
     * 休息时、预约满，空闲圆角
     */
    private float[] restRadius;
    /**
     * 未预约满时的圆角
     */
    private float[] partRadius;
    /**
     * Shape自定义
     */
    GradientDrawable drawable;
    /**
     * 休息填充颜色
     */
    private int restFillColor;
    /**
     * 预约满的颜色
     */
    private int fullFillColor;
    /**
     * 未预约满颜色
     */
    private int partFillColor;
    /**
     * 空闲颜色
     */
    private int freeFillColor;
    /**
     * 控件宽度，高度
     */
    private int width, height;
    /**
     * 宽度比例
     */
    private double prent;

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //设置默认颜色
        restFillColor = ContextCompat.getColor(context, R.color.time_bg_rest);
        fullFillColor = ContextCompat.getColor(context, R.color.time_bg_full);
        partFillColor = ContextCompat.getColor(context, R.color.time_bg_part);
        freeFillColor = ContextCompat.getColor(context, R.color.time_bg_free);
        //设置默认圆角
        int radius = DensityUtils.dp2px(context, getResources().getDimension(R.dimen.time_radius));
        restRadius = new float[]{0, 0, 0, 0, radius, radius, radius, radius};
        partRadius = new float[]{0, 0, 0, 0, 0, 0, radius, radius};
//初始化参数
        drawable = new GradientDrawable();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (prent != 0 ) {
            height = View.MeasureSpec.getSize(heightMeasureSpec);
            width = (int) (MeasureSpec.getSize(widthMeasureSpec) * prent);
            setMeasuredDimension(width, height);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 绘制休息控件颜色
     */
    public void drawRest() {
        prent = 0;
        drawable.setColor(restFillColor);
        drawable.setCornerRadii(restRadius);
        setBackgroundDrawable(drawable);
    }

    /**
     * 绘制预约满的控件颜色
     */
    public void drawFull() {
        prent = 0;
        drawable.setColor(fullFillColor);
        drawable.setCornerRadii(restRadius);
        setBackgroundDrawable(drawable);
    }

    /**
     * 绘制部分控件颜色
     */
    public void drawPart(int useTime, int totalTime) {
        prent = (double) useTime / (double) totalTime;
        drawable.setColor(partFillColor);
        drawable.setCornerRadii(partRadius);
        setBackgroundDrawable(drawable);
    }

    /**
     * 空闲控件绘制
     */
    public void drawFree(){
        prent = 0;
        drawable.setColor(freeFillColor);
        drawable.setCornerRadii(restRadius);
        setBackgroundDrawable(drawable);
    }

}
