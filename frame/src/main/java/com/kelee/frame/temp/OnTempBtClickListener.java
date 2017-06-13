package com.kelee.frame.temp;

import android.view.View;

/**
 * Created by kelee on 2017-06-01.
 * 填充界面监听
 */

public interface OnTempBtClickListener {
    /**
     * @param type {@link ITempView#ERROR}, {@link ITempView#DATA_NULL}, {@link ITempView#LOADING}
     */
    public void onBtTempClick(View view, int type);
}
