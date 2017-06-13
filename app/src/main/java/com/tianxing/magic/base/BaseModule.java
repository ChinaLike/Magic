package com.tianxing.magic.base;

import android.content.Context;

import com.kelee.frame.module.AbsModule;
import com.kelee.frame.util.AESEncryption;
import com.tianxing.magic.config.Constance;
import com.tianxing.magic.entity.info.ShopInfo;
import com.tianxing.magic.module.UserModule;

/**
 * Created by kelee on 2017-06-07.
 */

public class BaseModule extends AbsModule {

    public BaseModule(Context context) {
        super(context);
    }

    /**
     * 获取一个Key加密，即MFS+（）一个值
     *
     * @return
     */
    protected String getOneKey(String key) {
        String str = Constance.KEY.MFS + "," + key;
        return AESEncryption.encrypt(ShopInfo.key, str);
    }

    /**
     * 获取一个Key加密，即MFS+（）一个值+()一个值
     * @param key1
     * @param key2
     * @return
     */
    protected String getDoubleKey(String key1 ,String key2){
        String str = Constance.KEY.MFS + "," + key1 + "," + key2;
        return AESEncryption.encrypt(ShopInfo.key, str);
    }

}
