package com.tianxing.magic.entity;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by kelee on 2017-06-12.
 * 预约提醒
 */

public class TipBean implements Serializable {
    private String TipTitle;          //提示标题
    private String TipDetail;          //提示明细

    public String getTipTitle() {
        if (TextUtils.isEmpty(TipTitle)){
            return "";
        }
        return TipTitle;
    }

    public void setTipTitle(String tipTitle) {
        TipTitle = tipTitle;
    }

    public String getTipDetail() {
        if (TextUtils.isEmpty(TipDetail)){
            return "";
        }
        return TipDetail;
    }

    public void setTipDetail(String tipDetail) {
        TipDetail = tipDetail;
    }

    @Override
    public String toString() {
        return "TipBean{" +
                "TipTitle='" + TipTitle + '\'' +
                ", TipDetail='" + TipDetail + '\'' +
                '}';
    }
}
