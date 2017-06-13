package com.tianxing.magic.http.inf;

import com.tianxing.magic.entity.BannerBean;
import com.tianxing.magic.entity.HairDresserBean;
import com.tianxing.magic.entity.ProjectBean;
import com.tianxing.magic.entity.ResponseBean;
import com.tianxing.magic.entity.StoreChooseBean;
import com.tianxing.magic.entity.TimeBean;
import com.tianxing.magic.entity.TipBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by kelee on 2017-06-08.
 * 请求接口
 */

public interface IRequest {

    /**
     * 首页Banner
     *
     * @param url
     * @param params
     * @return
     */
    @POST("{url}")
    Call<ResponseBean<List<BannerBean>>> banner(@Path("url") String url, @QueryMap Map<String, String> params);

    /**
     * 请求店铺列表
     *
     * @param url
     * @param params
     * @return
     */
    @POST("{url}")
    Call<ResponseBean<List<StoreChooseBean>>> storeList(@Path("url") String url, @QueryMap Map<String, String> params);

    /**
     * 请求发型师列表
     *
     * @param url
     * @param params
     * @return
     */
    @POST("{url}")
    Call<ResponseBean<List<HairDresserBean>>> hairdresserList(@Path("url") String url, @QueryMap Map<String, String> params);

    /**
     * 预约项目列表
     *
     * @param url
     * @param params
     * @return
     */
    @POST("{url}")
    Call<ResponseBean<List<ProjectBean>>> projectList(@Path("url") String url, @QueryMap Map<String, String> params);

    /**
     * 预约时间列表
     *
     * @param url
     * @param params
     * @return
     */
    @POST("{url}")
    Call<ResponseBean<List<TimeBean>>> projectTime(@Path("url") String url, @QueryMap Map<String, String> params);

    /**
     * 预约提醒说明
     *
     * @param url
     * @param params
     * @return
     */
    @POST("{url}")
    Call<ResponseBean<TipBean>> projectTip(@Path("url") String url, @QueryMap Map<String, String> params);


}
