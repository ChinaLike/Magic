package com.tianxing.magic.entity;

/**
 * Created by kelee on 2017-06-08.
 * 通用请求
 */

public class RequestBean {

    private String action;

    private String token;

    private String value;

    public RequestBean(String action, String value) {
        this.action = action;
        this.value = value;
    }

    public RequestBean(String action, String token, String value) {
        this.action = action;
        this.token = token;
        this.value = value;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
