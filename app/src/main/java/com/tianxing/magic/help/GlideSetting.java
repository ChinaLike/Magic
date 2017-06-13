package com.tianxing.magic.help;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by kelee on 2017-06-08.
 * Glide设置
 */

public class GlideSetting {

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }


    public static void loading(ImageView img, String url) {
        Glide.with(mContext).load(url).into(img);
    }

    public static void pauseRequests(){
        Glide.with(mContext).pauseRequests();
    }

    public static void resumeRequests(){
        Glide.with(mContext).resumeRequests();
    }

}
