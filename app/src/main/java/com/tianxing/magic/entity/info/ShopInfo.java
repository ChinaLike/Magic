package com.tianxing.magic.entity.info;

import com.tianxing.magic.entity.order.HairDresserBean;
import com.tianxing.magic.entity.order.PriceBean;
import com.tianxing.magic.entity.order.ProjectBean;
import com.tianxing.magic.entity.order.SubbranchBean;

import java.io.Serializable;

/**
 * Created by kelee on 2017-06-11.
 * 店铺所有信息
 */

public class ShopInfo implements Serializable {

    public static String token = "7A09D02C";//登录后的token

    public static String key = "D06621A5FBA7";//解密的Key

    private String shopId;//店铺ID

    private SubbranchBean subbranch;//分店信息

    private HairDresserBean designer;//发型师

    private ProjectBean project;//项目信息

    private PriceBean priceA;//价格信息（A项）

    private PriceBean priceB;//价格信息（B项）

    public String getShopId() {
//        return shopId;
        return "dsyr";
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public SubbranchBean getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(SubbranchBean subbranch) {
        this.subbranch = subbranch;
    }

    public ProjectBean getProject() {
        return project;
    }

    public void setProject(ProjectBean project) {
        this.project = project;
    }

    public PriceBean getPriceA() {
        return priceA;
    }

    public void setPriceA(PriceBean priceA) {
        this.priceA = priceA;
    }

    public PriceBean getPriceB() {
        return priceB;
    }

    public void setPriceB(PriceBean priceB) {
        this.priceB = priceB;
    }

    public HairDresserBean getDesigner() {
        return designer;
    }

    public void setDesigner(HairDresserBean designer) {
        this.designer = designer;
    }

    @Override
    public String toString() {
        return "ShopInfo{" +
                "token='" + token + '\'' +
                ", key='" + key + '\'' +
                ", shopId='" + shopId + '\'' +
                ", stroe=" + subbranch +
                ", dresser=" + designer +
                ", project=" + project +
                ", priceA=" + priceA +
                ", priceB=" + priceB +
                '}';
    }
}
