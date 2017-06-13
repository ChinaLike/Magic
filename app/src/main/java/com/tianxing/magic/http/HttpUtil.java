package com.tianxing.magic.http;

import com.tianxing.magic.http.inf.IRequest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kelee on 2017-06-05.
 * Retrofit数据请求
 */

public class HttpUtil {

    private static HttpUtil mHttpUtil;
    private static final Object LOCK = new Object();

    private HttpUtil() {

    }

    public static HttpUtil getInstance() {
        if (mHttpUtil == null) {
            synchronized (LOCK) {
                if (mHttpUtil == null) {
                    mHttpUtil = new HttpUtil();
                }
            }
        }
        return mHttpUtil;
    }

    public IRequest getService(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IRequest service = retrofit.create(IRequest.class);

        return service;
    }

}
