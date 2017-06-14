package com.tianxing.magic.config;

import com.kelee.frame.util.CalendarUtils;

/**
 * Created by kelee on 2017-06-07.
 * 配置参数
 */

public class Constance {

    public static class KEY {
        //AES加密中需要的一个常量
        public static final String MFS = "MFS";
        //初始AES的Key
        public static final String AES_KEY = "mofashi" + CalendarUtils.getNowData();

        public static final String ACTION = "action";
        public static final String VALUE = "value";
        public static final String TOKEN = "token";

        //门店信息
        public static final String STROE_INFO = "storeInfo";
        //发型师信息
        public static final String HAIRDRESSER_INFO = "hairdresserInfo";
        //店铺信息
        public static final String SHOP_INFO = "shopInfo";
        //交流圈信息
        public static final String COMM_INFO = "commInfo";

    }

    /**
     * 请求数据Action
     */
    public static class ACTION {
        //用户登录
        public static final String LOGIN = "login";
        //首页Banner
        public static final String BANNER = "getbanner";
        //获取店铺列表
        public static final String BRANCH = "getbranch";
        //获取发型师列表
        public static final String HAIRDRESSER = "getdesigner";
        //获取项目列表
        public static final String PROJECT = "getproject";
        //获取项目时间
        public static final String PROJECT_TIME = "getappointment";
        //获取预约提醒
        public static final String PROJECT_TIP = "gettips";
        //交流圈-获取用户信息
        public static final String COMM_USER_INFO = "getuserinfo";
        //交流圈-获取交流圈列表
        public static final String COMM_LIST = "getmoments";
    }

}
