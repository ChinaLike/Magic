package com.tianxing.magic.entity.communication;

import java.io.Serializable;

/**
 * Created by kelee on 2017-06-13.
 * 交流圈用户信息
 */

public class UserInfoBean implements Serializable {

    private int errcode;
    private String NickName;//昵称
    private String Signature;//签名
    private String headImage;//头像
    private String backImage;//背景

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "errcode=" + errcode +
                ", NickName='" + NickName + '\'' +
                ", Signature='" + Signature + '\'' +
                ", headImage='" + headImage + '\'' +
                ", backImage='" + backImage + '\'' +
                '}';
    }
}
