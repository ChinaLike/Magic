package com.tianxing.magic.entity;

/**
 * Created by kelee on 2017-06-10.
 * 首页Banner
 */

public class BannerBean {

    private int HDID;//促销活动代码

    private String PicUrl;//图片路径

    public int getHDID() {
        return HDID;
    }

    public void setHDID(int HDID) {
        this.HDID = HDID;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "HDID=" + HDID +
                ", PicUrl='" + PicUrl + '\'' +
                '}';
    }
}
