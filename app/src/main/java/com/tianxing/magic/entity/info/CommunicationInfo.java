package com.tianxing.magic.entity.info;

import com.tianxing.magic.entity.user.UserBean;

import java.io.Serializable;

/**
 * Created by kelee on 2017-06-13.
 * 交流圈信息
 */

public class CommunicationInfo implements Serializable {

    private UserBean user;//用户信息

    public UserBean getUser() {
        if (user == null){
            return new UserBean();
        }
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
