package com.tianxing.magic.module;

import android.content.Context;

import com.kelee.frame.util.L;
import com.kelee.frame.util.T;
import com.tianxing.magic.R;
import com.tianxing.magic.config.Constance;
import com.tianxing.magic.config.ResultCode;
import com.tianxing.magic.databinding.ActivityTimeChooseBinding;
import com.tianxing.magic.entity.HairDresserBean;
import com.tianxing.magic.entity.ProjectBean;
import com.tianxing.magic.entity.ResponseBean;
import com.tianxing.magic.entity.TimeBean;
import com.tianxing.magic.entity.TipBean;
import com.tianxing.magic.entity.info.ShopInfo;
import com.tianxing.magic.http.HttpUtil;
import com.tianxing.magic.base.BaseModule;
import com.tianxing.magic.config.ServerConstance;
import com.tianxing.magic.entity.StoreChooseBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kelee on 2017-06-07.
 * 美发预约:店家选择、美发师选择、项目选择、预约时间选择
 * 数据请求
 */

public class OrderModule extends BaseModule {

    private Context mContext;

    public OrderModule(Context context) {
        super(context);
        mContext = context;
    }

    /**
     * 店铺选择列表
     *
     * @param StoreId 店铺ID
     */
    public void storeChoose(String StoreId) {
        Map<String, String> map = new HashMap<>();
        map.put(Constance.KEY.ACTION, Constance.ACTION.BRANCH);
        map.put(Constance.KEY.TOKEN, ShopInfo.token);
        map.put(Constance.KEY.VALUE, getOneKey(StoreId));
        HttpUtil.getInstance().getService(ServerConstance.baseUrl)
                .storeList(ServerConstance.url, map)
                .enqueue(new Callback<ResponseBean<List<StoreChooseBean>>>() {
                    @Override
                    public void onResponse(Call<ResponseBean<List<StoreChooseBean>>> call, Response<ResponseBean<List<StoreChooseBean>>> response) {
                        ResponseBean<List<StoreChooseBean>> bean = response.body();
                        if (bean != null) {
                            if (bean.getErrcode() == 0) {
                                callback(ResultCode.SERVICE.STROE_LIST, bean.getData());
                            } else {
                                callback(ResultCode.SERVICE.FAILER, bean.getErrdesc());
                            }
                        } else {
                            callback(ResultCode.SERVICE.FAILER, mContext.getString(R.string.error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBean<List<StoreChooseBean>>> call, Throwable t) {
                        T.showShort(mContext, mContext.getString(R.string.over_time));
                    }
                });
    }

    /**
     * 获取发型师
     *
     * @param childStoreId 分店ID
     */
    public void hairdresserChoose(String childStoreId) {
        Map<String, String> map = new HashMap<>();
        map.put(Constance.KEY.ACTION, Constance.ACTION.HAIRDRESSER);
        map.put(Constance.KEY.TOKEN, ShopInfo.token);
        map.put(Constance.KEY.VALUE, getOneKey(childStoreId));
        HttpUtil.getInstance().getService(ServerConstance.baseUrl)
                .hairdresserList(ServerConstance.url, map)
                .enqueue(new Callback<ResponseBean<List<HairDresserBean>>>() {
                    @Override
                    public void onResponse(Call<ResponseBean<List<HairDresserBean>>> call, Response<ResponseBean<List<HairDresserBean>>> response) {
                        ResponseBean<List<HairDresserBean>> bean = response.body();
                        if (bean != null) {
                            if (bean.getErrcode() == 0) {
                                callback(ResultCode.SERVICE.HAIRDRESSER_LIST, bean.getData());
                            } else {
                                callback(ResultCode.SERVICE.FAILER, bean.getErrdesc());
                            }
                        } else {
                            callback(ResultCode.SERVICE.FAILER, mContext.getString(R.string.error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBean<List<HairDresserBean>>> call, Throwable t) {
                        T.showShort(mContext, mContext.getString(R.string.over_time));
                    }
                });
    }

    /**
     * 获取预约项目列表
     *
     * @param storeId       分店ID
     * @param hairdresserId 发型师ID
     */
    public void projectChoose(String storeId, int hairdresserId) {
        Map<String, String> map = new HashMap<>();
        map.put(Constance.KEY.ACTION, Constance.ACTION.PROJECT);
        map.put(Constance.KEY.TOKEN, ShopInfo.token);
        map.put(Constance.KEY.VALUE, getDoubleKey(storeId + "", hairdresserId + ""));
        HttpUtil.getInstance().getService(ServerConstance.baseUrl)
                .projectList(ServerConstance.url, map)
                .enqueue(new Callback<ResponseBean<List<ProjectBean>>>() {
                    @Override
                    public void onResponse(Call<ResponseBean<List<ProjectBean>>> call, Response<ResponseBean<List<ProjectBean>>> response) {
                        ResponseBean<List<ProjectBean>> bean = response.body();
                        if (bean != null) {
                            if (bean.getErrcode() == 0) {
                                callback(ResultCode.SERVICE.PROJECT_LIST, bean.getData());
                            } else {
                                callback(ResultCode.SERVICE.FAILER, bean.getErrdesc());
                            }
                        } else {
                            callback(ResultCode.SERVICE.FAILER, mContext.getString(R.string.error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBean<List<ProjectBean>>> call, Throwable t) {
                        T.showShort(mContext, mContext.getString(R.string.over_time));
                    }
                });
    }

    /**
     * 获取预约时间列表
     *
     * @param dresserId
     * @param data
     */
    public void timeChoose(int dresserId, String data) {
        Map<String, String> map = new HashMap<>();
        map.put(Constance.KEY.ACTION, Constance.ACTION.PROJECT_TIME);
        map.put(Constance.KEY.TOKEN, ShopInfo.token);
        map.put(Constance.KEY.VALUE, getDoubleKey(dresserId + "", data + ""));
        L.d("请求：", getDoubleKey(dresserId + "", data + ""));
        HttpUtil.getInstance().getService(ServerConstance.baseUrl)
                .projectTime(ServerConstance.url, map)
                .enqueue(new Callback<ResponseBean<List<TimeBean>>>() {
                    @Override
                    public void onResponse(Call<ResponseBean<List<TimeBean>>> call, Response<ResponseBean<List<TimeBean>>> response) {
                        ResponseBean<List<TimeBean>> bean = response.body();
                        if (bean != null) {
                            if (bean.getErrcode() == 0) {
                                callback(ResultCode.SERVICE.TIME_LIST, bean.getData());
                            } else {
                                callback(ResultCode.SERVICE.FAILER, bean.getErrdesc());
                            }
                        } else {
                            callback(ResultCode.SERVICE.FAILER, mContext.getString(R.string.error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBean<List<TimeBean>>> call, Throwable t) {
                        T.showShort(mContext, mContext.getString(R.string.over_time));
                    }
                });
    }

    /**
     * 获取预约提醒信息
     *
     * @param dresserId
     * @param data
     */
    public void tip(int dresserId, String data) {
        Map<String, String> map = new HashMap<>();
        map.put(Constance.KEY.ACTION, Constance.ACTION.PROJECT_TIP);
        map.put(Constance.KEY.TOKEN, ShopInfo.token);
        map.put(Constance.KEY.VALUE, getDoubleKey(dresserId + "", data + ""));
        HttpUtil.getInstance().getService(ServerConstance.baseUrl)
                .projectTip(ServerConstance.url, map)
                .enqueue(new Callback<ResponseBean<TipBean>>() {
                    @Override
                    public void onResponse(Call<ResponseBean<TipBean>> call, Response<ResponseBean<TipBean>> response) {
                        ResponseBean<TipBean> bean = response.body();
                        if (bean != null) {
                            if (bean.getErrcode() == 0 && bean.getData() != null) {
                                getBinding(ActivityTimeChooseBinding.class).tipTitle.setText(bean.getData().getTipTitle()+"");
                                getBinding(ActivityTimeChooseBinding.class).tipContent.setText(bean.getData().getTipDetail()+"");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBean<TipBean>> call, Throwable t) {
                        T.showShort(mContext, mContext.getString(R.string.over_time));
                    }
                });
    }

}
