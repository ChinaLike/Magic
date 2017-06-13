package com.tianxing.magic.module;

import android.content.Context;

import com.kelee.frame.module.AbsModule;
import com.kelee.frame.util.T;
import com.tianxing.magic.R;
import com.tianxing.magic.base.BaseModule;
import com.tianxing.magic.config.Constance;
import com.tianxing.magic.config.ResultCode;
import com.tianxing.magic.config.ServerConstance;
import com.tianxing.magic.entity.BannerBean;
import com.tianxing.magic.entity.ProjectBean;
import com.tianxing.magic.entity.ResponseBean;
import com.tianxing.magic.entity.info.ShopInfo;
import com.tianxing.magic.http.HttpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kelee on 2017-06-10.
 * 首页数据请求
 */

public class MainModule extends BaseModule{

    private Context mContext;

    public MainModule(Context context) {
        super(context);
        this.mContext = context;
    }

    public void banner(String shopId){
        Map<String,String> map = new HashMap<>();
        map.put(Constance.KEY.ACTION, Constance.ACTION.BANNER);
        map.put(Constance.KEY.TOKEN, ShopInfo.token);
        map.put(Constance.KEY.VALUE, getOneKey(shopId));
        HttpUtil.getInstance().getService(ServerConstance.baseUrl)
                .banner(ServerConstance.url, map)
                .enqueue(new Callback<ResponseBean<List<BannerBean>>>() {
                    @Override
                    public void onResponse(Call<ResponseBean<List<BannerBean>>> call, Response<ResponseBean<List<BannerBean>>> response) {
                        ResponseBean<List<BannerBean>> bean = response.body();
                        if (bean != null) {
                            if (bean.getErrcode() == 0) {
                                callback(ResultCode.SERVICE.BANNER_LIST, bean.getData());
                            } else {
                                callback(ResultCode.SERVICE.FAILER, bean.getErrdesc());
                            }
                        } else {
                            callback(ResultCode.SERVICE.FAILER, mContext.getString(R.string.error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBean<List<BannerBean>>> call, Throwable t) {
                        T.showShort(mContext, mContext.getString(R.string.over_time));
                    }
                });

    }

}
