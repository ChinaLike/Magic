package com.tianxing.magic.entity;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelee on 2017-06-08.
 * 项目预约-项目
 */

public class ProjectBean implements Serializable {
    private String HeadImage;
    private String ProjectID;//项目id
    private String ItemA;           //项目名称A
    private List<Integer> PriceA;
    private String ItemB;//项目名称B
    private List<Integer> PriceB;
    private String NeedMins;//花费时间

    private List<PriceBean> priceAList = new ArrayList<>();

    private List<PriceBean> priceBList = new ArrayList<>();

    private boolean isSelect;//Button是否被选中

    private int position = -1;

    public String getHeadImage() {
        return HeadImage;
    }

    public void setHeadImage(String headImage) {
        HeadImage = headImage;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public String getItemA() {
        return ItemA;
    }

    public void setItemA(String itemA) {
        ItemA = itemA;
    }

    public List<Integer> getPriceA() {
        return PriceA;
    }

    public void setPriceA(List<Integer> priceA) {
        PriceA = priceA;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public String getItemB() {
        return ItemB;
    }

    public void setItemB(String itemB) {
        ItemB = itemB;
    }

    public List<Integer> getPriceB() {
        return PriceB;
    }

    public void setPriceB(List<Integer> priceB) {
        PriceB = priceB;
    }

    public String getNeedMins() {
        return NeedMins;
    }

    public void setNeedMins(String needMins) {
        NeedMins = needMins;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    /**
     * 判断是否是双项目
     *
     * @return
     */
    public boolean isDoubleProject() {
        if (priceAList != null && priceAList.size() > 0 && priceBList != null && priceBList.size() > 0)
            return true;
        else
            return false;
    }

    public boolean isAProject() {
        if (priceAList != null && priceAList.size() > 0 && (priceBList == null || priceBList.size() == 0))
            return true;
        else
            return false;
    }

    public boolean isBProject() {
        if (priceBList != null && priceBList.size() > 0 && (priceAList == null || priceAList.size() == 0))
            return true;
        else
            return false;
    }

    /**
     * 获取A项目价格列表
     *
     * @return
     */
    public List<PriceBean> getPriceAList() {
        return priceAList;
    }

    /**
     * 获取B项目价格列表
     *
     * @return
     */
    public List<PriceBean> getPriceBList() {
        return priceBList;
    }

    public void setPriceAList(List<Integer> priceA) {
        List<PriceBean> list = new ArrayList<>();
        if (priceA == null || priceA.size() == 0) {
            priceAList = list;
        } else {

            for (int i = 0; i < priceA.size(); i++) {
                PriceBean bean = new PriceBean();
                bean.setPrice(priceA.get(i)+"");
                list.add(bean);
            }
            priceAList = list;
        }
    }

    public void setPriceBList(List<Integer> priceB) {
        List<PriceBean> list = new ArrayList<>();
        if (priceB == null || priceB.size() == 0) {
            priceBList = list;
        } else {
            for (int i = 0; i < priceB.size(); i++) {
                PriceBean bean = new PriceBean();
                bean.setPrice(priceB.get(i)+"");
                list.add(bean);
            }
            priceBList = list;
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ProjectBean{" +
                "HeadImage='" + HeadImage + '\'' +
                ", ProjectID='" + ProjectID + '\'' +
                ", ItemA='" + ItemA + '\'' +
                ", PriceA='" + PriceA + '\'' +
                ", ItemB='" + ItemB + '\'' +
                ", PriceB='" + PriceB + '\'' +
                ", NeedMins='" + NeedMins + '\'' +
                ", priceAList=" + priceAList +
                ", priceBList=" + priceBList +
                ", isSelect=" + isSelect +
                '}';
    }
}
