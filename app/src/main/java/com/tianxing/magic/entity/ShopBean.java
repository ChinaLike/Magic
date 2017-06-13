package com.tianxing.magic.entity;

import java.io.Serializable;

/**
 * Created by kelee on 2017-06-10.
 * 店铺信息
 */

public class ShopBean implements Serializable{

    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
