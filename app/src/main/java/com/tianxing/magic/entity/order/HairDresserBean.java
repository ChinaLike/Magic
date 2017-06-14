package com.tianxing.magic.entity.order;

import java.io.Serializable;

/**
 * Created by kelee on 2017-06-08.
 * 发型师信息
 */

public class HairDresserBean implements Serializable{

    private int DesignerID;
    private String WorkID; //工号
    private String DesignerName;  //显示名称
    private String Mobile;
    private String HeadImage;

    public int getDesignerID() {
        return DesignerID;
    }

    public void setDesignerID(int designerID) {
        DesignerID = designerID;
    }

    public String getWorkID() {
        return WorkID;
    }

    public void setWorkID(String workID) {
        WorkID = workID;
    }

    public String getDesignerName() {
        return DesignerName;
    }

    public void setDesignerName(String designerName) {
        DesignerName = designerName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getHeadImage() {
        return HeadImage;
    }

    public void setHeadImage(String headImage) {
        HeadImage = headImage;
    }

    @Override
    public String toString() {
        return "HairDresserBean{" +
                "DesignerID=" + DesignerID +
                ", WorkID='" + WorkID + '\'' +
                ", DesignerName='" + DesignerName + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", HeadImage='" + HeadImage + '\'' +
                '}';
    }
}
