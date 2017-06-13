package com.tianxing.magic.module;

import android.content.Context;

import com.kelee.frame.module.AbsModule;
import com.kelee.frame.util.AESEncryption;
import com.kelee.frame.util.MD5;
import com.tianxing.magic.config.Constance;

/**
 * Created by kelee on 2017-06-08.
 * 用户有关：用户登录
 */

public class UserModule extends AbsModule {

    public UserModule(Context context) {
        super(context);
    }

    /**
     * 用户登录
     * @param userName  用户名
     * @param password  密码
     */
    public void login(String userName , String password){
        StringBuilder value = new StringBuilder();
        value.append(Constance.KEY.MFS).append(",").append(userName).append(MD5.encrypt(password));
        String valueAES = AESEncryption.encrypt(Constance.KEY.AES_KEY,value.toString());
    }

}
