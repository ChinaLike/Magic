package com.tianxing.magic.entity.order;

import java.io.Serializable;

/**
 * Created by kelee on 2017-06-09.
 * 预约项目下的金额
 */

public class PriceBean implements Serializable{
    private int id;
    private String price;
    private boolean isSelect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "PriceBean{" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }
}
