package com.kelee.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.Log;

import com.kelee.ui.R;

import java.lang.reflect.Field;

/**
 * Created by kelee on 2017-06-12.
 * 指定一页显示标签个数
 */

public class MyTabLayout extends TabLayout {
    /**
     * 一页显示的个数
     */
    private int number = 5;

    private static final String SCROLLABLE_TAB_MIN_WIDTH = "mScrollableTabMinWidth";

    public MyTabLayout(Context context) {
        this(context, null);
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTabLayout, defStyleAttr, 0);
        number = array.getInt(R.styleable.MyTabLayout_show_number, -1);
        array.recycle();
        initTabMinWidth();
    }

    private void initTabMinWidth() {
        if (number != -1) {
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            int tabMinWidth = screenWidth / number;
            try {
                Field field = TabLayout.class.getDeclaredField(SCROLLABLE_TAB_MIN_WIDTH);
                field.setAccessible(true);
                field.set(this, tabMinWidth);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
