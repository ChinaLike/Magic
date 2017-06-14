package com.tianxing.magic.entity;

import android.text.TextUtils;

import com.kelee.frame.util.T;

import java.util.List;

/**
 * Created by kelee on 2017-06-08.
 * 通用响应
 */

public class ResponseBean<T> {
    private int errcode;
    private String errdesc;
    private T data;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrdesc() {
        if (TextUtils.isEmpty(errdesc)) {
            return "获取数据失败";
        }
        return errdesc;
    }

    public void setErrdesc(String errdesc) {
        this.errdesc = errdesc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "errcode=" + errcode +
                ", errdesc='" + errdesc + '\'' +
                ", data=" + data +
                '}';
    }
}
