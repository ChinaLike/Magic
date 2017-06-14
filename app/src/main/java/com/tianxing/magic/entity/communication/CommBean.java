package com.tianxing.magic.entity.communication;

import java.util.List;

/**
 * Created by kelee on 2017-06-13.
 * 交流圈信息
 */

public class CommBean {

    private String MsgID;
    private String msgContent;
    private String msgTime;
    private String NickName;
    private String UserID;
    private String iconName;
    private List<String> msgPics;
    private List<String> msgLike;
    private List<String> Comments;

    public String getMsgID() {
        return MsgID;
    }

    public void setMsgID(String msgID) {
        MsgID = msgID;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public List<String> getMsgPics() {
        return msgPics;
    }

    public void setMsgPics(List<String> msgPics) {
        this.msgPics = msgPics;
    }

    public List<String> getMsgLike() {
        return msgLike;
    }

    public void setMsgLike(List<String> msgLike) {
        this.msgLike = msgLike;
    }

    public List<String> getComments() {
        return Comments;
    }

    public void setComments(List<String> comments) {
        Comments = comments;
    }
}
