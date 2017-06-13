package com.tianxing.magic.help;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tianxing.magic.entity.BannerBean;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by kelee on 2017-06-05.
 * Banner图片加载器
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        BannerBean bean = (BannerBean) path;
        Glide.with(context).load(bean.getPicUrl()).into(imageView);
    }
}
