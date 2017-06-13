package com.kelee.frame.core;

import android.content.Context;

import com.kelee.frame.module.AbsModule;

/**
 * Created by kelee on 2017-06-03.
 */

public class DialogSimpleModule extends AbsModule{

    public DialogSimpleModule(Context context) {
        super(context);
    }

    /**
     * 统一的回调
     */
    public void onDialog(int result, Object data) {
        callback(result, data);
    }

}
