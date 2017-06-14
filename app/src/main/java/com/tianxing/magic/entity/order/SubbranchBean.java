package com.tianxing.magic.entity.order;

import java.io.Serializable;

/**
 * Created by kelee on 2017-06-08.
 * 门店信息
 */

public class SubbranchBean implements Serializable{

    private String fdBranchID;
    private String fdPicURL;
    private String fdName;
    private String fdAddr;
    private String fdTel;
    private String OpeningTime;
    private String Duration;

    public String getFdBranchID() {
        return fdBranchID;
    }

    public void setFdBranchID(String fdBranchID) {
        this.fdBranchID = fdBranchID;
    }

    public String getFdPicURL() {
        return fdPicURL;
    }

    public void setFdPicURL(String fdPicURL) {
        this.fdPicURL = fdPicURL;
    }

    public String getFdName() {
        return fdName;
    }

    public void setFdName(String fdName) {
        this.fdName = fdName;
    }

    public String getFdAddr() {
        return fdAddr;
    }

    public void setFdAddr(String fdAddr) {
        this.fdAddr = fdAddr;
    }

    public String getFdTel() {
        return fdTel;
    }

    public void setFdTel(String fdTel) {
        this.fdTel = fdTel;
    }

    public String getOpeningTime() {
        return OpeningTime;
    }

    public void setOpeningTime(String openingTime) {
        OpeningTime = openingTime;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    @Override
    public String toString() {
        return "SubbranchBean{" +
                "fdBranchID='" + fdBranchID + '\'' +
                ", fdPicURL='" + fdPicURL + '\'' +
                ", fdName='" + fdName + '\'' +
                ", fdAddr='" + fdAddr + '\'' +
                ", fdTel='" + fdTel + '\'' +
                ", OpeningTime='" + OpeningTime + '\'' +
                ", Duration='" + Duration + '\'' +
                '}';
    }
}
