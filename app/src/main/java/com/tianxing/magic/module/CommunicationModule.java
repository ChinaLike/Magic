package com.tianxing.magic.module;

import android.content.Context;

import com.kelee.frame.util.T;
import com.tianxing.magic.R;
import com.tianxing.magic.base.BaseModule;
import com.tianxing.magic.config.Constance;
import com.tianxing.magic.config.ResultCode;
import com.tianxing.magic.config.ServerConstance;
import com.tianxing.magic.entity.ResponseBean;
import com.tianxing.magic.entity.communication.CommBean;
import com.tianxing.magic.entity.communication.UserInfoBean;
import com.tianxing.magic.entity.info.ShopInfo;
import com.tianxing.magic.entity.order.ProjectBean;
import com.tianxing.magic.http.HttpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kelee on 2017-06-13.
 * 用户交流有关数据请求
 */

public class CommunicationModule extends BaseModule {

    private Context mContext;

    public CommunicationModule(Context context) {
        super(context);
        this.mContext = context;
    }

    /**
     * 获取用户信息
     *
     * @param userId
     */
    public void userInfo(int userId) {
        Map<String, String> map = new HashMap<>();
        map.put(Constance.KEY.ACTION, Constance.ACTION.COMM_USER_INFO);
        map.put(Constance.KEY.TOKEN, ShopInfo.token);
        map.put(Constance.KEY.VALUE, getOneKey(userId + ""));
        HttpUtil.getInstance().getService(ServerConstance.baseUrl)
                .commUserInfo(map)
                .enqueue(new Callback<UserInfoBean>() {
                    @Override
                    public void onResponse(Call<UserInfoBean> call, Response<UserInfoBean> response) {
                        UserInfoBean bean = response.body();
                        if (bean != null && bean.getErrcode() == 0) {
                            callback(ResultCode.SERVICE.COMM_USER, bean);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserInfoBean> call, Throwable t) {
                        T.showShort(mContext, mContext.getString(R.string.over_time));
                    }
                });
    }

    /**
     * 获取评论列表
     * @param number    指定数量返回
     * @param id    -1时最新数据，其他：查询指定ID内容
     */
    public void commList(int number , int id){
        Map<String, String> map = new HashMap<>();
        map.put(Constance.KEY.ACTION, Constance.ACTION.COMM_LIST);
        map.put(Constance.KEY.TOKEN, ShopInfo.token);
        map.put(Constance.KEY.VALUE, getDoubleKey(number + "", id + ""));
        HttpUtil.getInstance().getService(ServerConstance.baseUrl)
                .commList(map)
                .enqueue(new Callback<ResponseBean<CommBean>>() {
                    @Override
                    public void onResponse(Call<ResponseBean<CommBean>> call, Response<ResponseBean<CommBean>> response) {
                        ResponseBean<CommBean> bean = response.body();
                        if (bean != null) {
                            if (bean.getErrcode() == 0) {
                                callback(ResultCode.SERVICE.COMM_LIST, bean.getData());
                            } else {
                                callback(ResultCode.SERVICE.FAILER, bean.getErrdesc());
                            }
                        } else {
                            callback(ResultCode.SERVICE.FAILER, mContext.getString(R.string.error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBean<CommBean>> call, Throwable t) {
                        T.showShort(mContext, mContext.getString(R.string.over_time));
                    }
                });
    }

}
