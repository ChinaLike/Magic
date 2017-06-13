package com.tianxing.magic.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kelee.frame.util.DensityUtils;
import com.tianxing.magic.R;

/**
 * Created by kelee on 2017-06-05.
 * 自定义ToolBar
 */

public class MyToolBar extends RelativeLayout {

    private TextView mTitle, leftText;

    private ImageView rightIcon;

    public MyToolBar(Context context) {
        this(context, null);
    }

    public MyToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this, true);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyToolBar, defStyleAttr, 0);
        CharSequence title, left;
        Drawable leftDrawable, rightDrawable;
        title = a.getString(R.styleable.MyToolBar_mt_title);
        left = a.getString(R.styleable.MyToolBar_mt_left_text);
        leftDrawable = a.getDrawable(R.styleable.MyToolBar_mt_left_icon);
        rightDrawable = a.getDrawable(R.styleable.MyToolBar_mt_right_icon);
        a.recycle();
        mTitle = (TextView) findViewById(R.id.title);
        leftText = (TextView) findViewById(R.id.left_text);
        rightIcon = (ImageView) findViewById(R.id.right_icon);
        //设置中间标题
        mTitle.setText(title);
        //设置右边图标
        if (rightDrawable != null) {
            rightIcon.setPadding(DensityUtils.dp2px(10),DensityUtils.dp2px(10),
                    DensityUtils.dp2px(10),DensityUtils.dp2px(10));
            rightIcon.setImageDrawable(rightDrawable);
        }
        leftText.setCompoundDrawablePadding(DensityUtils.dp2px(5));
        //设置左边
        setLeftIcon(leftDrawable, left);
    }

    /**
     * 设置左边图标与文字
     *
     * @param drawable
     * @param text
     */
    public void setLeftIcon(Drawable drawable, CharSequence text) {
        int size = DensityUtils.dp2px(10);
        leftText.setTextColor(Color.WHITE);
        if (drawable != null || text != null){
            leftText.setPadding(size,size,size,size);
        }
        if (text != null) {
            leftText.setText(text);
        }
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            leftText.setCompoundDrawables(drawable, null, null, null);
        }
    }


    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        }
    }

    /**
     * 设置右边图标
     *
     * @param drawable
     */
    public void setRightIcon(Drawable drawable) {
        if (drawable != null) {
            rightIcon.setPadding(DensityUtils.dp2px(10),DensityUtils.dp2px(10),
                    DensityUtils.dp2px(10),DensityUtils.dp2px(10));
            rightIcon.setImageDrawable(drawable);
        }
    }

    /**
     * 设置右边图标
     *
     * @param resId
     */
    public void setRightIcon(int resId) {
        rightIcon.setPadding(DensityUtils.dp2px(10),DensityUtils.dp2px(10),
                DensityUtils.dp2px(10),DensityUtils.dp2px(10));
        rightIcon.setImageResource(resId);
    }

    /**
     * 获取标题控件
     *
     * @return
     */
    public TextView getTitle() {
        return mTitle;
    }

    /**
     * 获取右边图片控件
     *
     * @return
     */
    public ImageView getRightIcon() {
        return rightIcon;
    }

    /**
     * 获取左边控件
     * @return
     */
    public TextView getLeftText(){
        return leftText;
    }

    /**
     * 设置左边监听
     * @param leftListener
     */
    public void setLeftListener(OnClickListener leftListener){
        leftText.setOnClickListener(leftListener);
    }

    /**
     * 设置右边监听
     * @param rightListener
     */
    public void setRightListener(OnClickListener rightListener){
        rightIcon.setOnClickListener(rightListener);
    }


}
