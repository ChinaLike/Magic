package com.tianxing.magic.config;

/**
 * Created by kelee on 2017-06-08.
 * 回调吗
 */

public class ResultCode {

    public static class SERVICE {

        //成功
        public static final int SUCCESS = 0x01;
        //失败
        public static final int FAILER = 0x02;
        //Banner
        public static final int BANNER_LIST = 0x001;

        //预约-分店列表
        public static final int STROE_LIST = 0xaa0;
        //预约-分店列表-发型师列表
        public static final int HAIRDRESSER_LIST = 0xaa1;
        //预约-分店列表-发型师列表-项目列表
        public static final int PROJECT_LIST = 0xaa2;
        //预约-分店列表-发型师列表-项目列表-预约时间
        public static final int TIME_LIST = 0xaa3;

        //交流圈-交流圈主页-用户信息
        public static final int COMM_USER = 0xab0;
        //交流圈-交流圈信息列表
        public static final int COMM_LIST = 0xab1;

    }

}
