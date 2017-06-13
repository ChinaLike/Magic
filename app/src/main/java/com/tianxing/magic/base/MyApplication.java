package com.tianxing.magic.base;

import android.app.Application;

import com.kelee.frame.core.AbsFrame;
import com.tianxing.magic.help.GlideSetting;

/**
 * Created by kelee on 2017-06-05.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AbsFrame.init(this);
        GlideSetting.init(getApplicationContext());
    }
}
