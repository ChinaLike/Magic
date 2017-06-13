package com.tianxing.magic.entity;

import android.text.TextUtils;

import com.kelee.frame.util.CalendarUtils;

import java.io.Serializable;

/**
 * Created by kelee on 2017-06-12.
 * 预约时间
 */

public class TimeBean implements Serializable {
    private String PartTime;//时间段
    private int UsedMins;//已占用多少分钟
    private int rest;//1为此时间休息
    private long spareValue;//剩余的连续可用时间

    public String getPartTime() {
        if (!TextUtils.isEmpty(PartTime)) {
            return CalendarUtils.getTime(PartTime);
        }
        return PartTime;
    }

    public void setPartTime(String partTime) {
        PartTime = partTime;
    }

    public int getUsedMins() {
        return UsedMins;
    }

    public void setUsedMins(int usedMins) {
        UsedMins = usedMins;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public long getSpareValue() {
        return spareValue;
    }

    public void setSpareValue(long spareValue) {
        this.spareValue = spareValue;
    }

    @Override
    public String toString() {
        return "TimeBean{" +
                "PartTime='" + PartTime + '\'' +
                ", UsedMins=" + UsedMins +
                ", rest=" + rest +
                ", spareValue=" + spareValue +
                '}';
    }
}
