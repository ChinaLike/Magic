package com.tianxing.magic.entity.user;

/**
 * Created by kelee on 2017-06-08.
 * 用户登录、用户注册
 */

public class UserBean {

    private int errcode;//错误代码，成功为0，失败非0
    private String errdesc;//失败时才有此字段，错误描述
    private String token;//登录token
    private String info;//之后通讯使用的AES密码，已使用AES加密
    private int userid;//登录用户id

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrdesc() {
        return errdesc;
    }

    public void setErrdesc(String errdesc) {
        this.errdesc = errdesc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getUserid() {
//        return userid;
        // TODO: 2017/6/13/013 修改
        return 1;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "errcode=" + errcode +
                ", errdesc='" + errdesc + '\'' +
                ", token='" + token + '\'' +
                ", info='" + info + '\'' +
                ", userid=" + userid +
                '}';
    }
}
