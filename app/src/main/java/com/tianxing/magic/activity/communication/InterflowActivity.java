package com.tianxing.magic.activity.communication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tianxing.magic.R;
import com.tianxing.magic.adapter.delagate.WordMorePictureDelagate;
import com.tianxing.magic.base.BaseActivity;
import com.tianxing.magic.config.ResultCode;
import com.tianxing.magic.databinding.ActivityInterflowBinding;
import com.tianxing.magic.entity.communication.CommBean;
import com.tianxing.magic.entity.communication.UserInfoBean;
import com.tianxing.magic.help.GlideSetting;
import com.tianxing.magic.module.CommunicationModule;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelee on 2017-06-06.
 * 交流圈的首页
 */

public class InterflowActivity extends BaseActivity<ActivityInterflowBinding> {
    /**
     * 用户背景图片
     */
    private ImageView userBackground;
    /**
     * 用户头像
     */
    private ImageView userHeader;
    /**
     * 用户信息
     */
    private UserInfoBean userInfo;
    /**
     * 头部视图
     */
    private View headerView;
    /**
     * 评论列表适配器
     */
    private MultiItemTypeAdapter adapter;
    /**
     * 头部适配器
     */
    private HeaderAndFooterWrapper wrapper;
    /**
     * 列表数据
     */
    private List<CommBean> commList = new ArrayList<>();

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        settingRecyclerView(getBinding().commRecycler);
        initAdapter();
        data();

    }

    /**
     * 初始化头部视图
     */
    private void initHeader() {
        wrapper = new HeaderAndFooterWrapper(adapter);
        headerView = LayoutInflater.from(this).inflate(R.layout.item_comm_main_header, (ViewGroup) findViewById(android.R.id.content), false);
        userBackground = (ImageView) headerView.findViewById(R.id.user_background);
        userHeader = (ImageView) headerView.findViewById(R.id.user_header);
        wrapper.addHeaderView(headerView);
        getBinding().commRecycler.setAdapter(wrapper);
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        adapter = new MultiItemTypeAdapter(this, commList);
        adapter.addItemViewDelegate(new WordMorePictureDelagate());
        initHeader();
    }

    /**
     * 适配用户数据
     *
     * @param bean
     */
    private void adapterUser(UserInfoBean bean) {
        GlideSetting.loading(userBackground, bean.getBackImage());
        GlideSetting.loading(userHeader, bean.getHeadImage());
        wrapper.notifyDataSetChanged();
    }


    /**
     * 获取数据
     */
    private void data() {
        if (userInfo == null || userInfo.getErrcode() != 0) {
            //用户数据未请求
            getModule(CommunicationModule.class).userInfo(getCommInfo().getUser().getUserid());
        }
//        getModule(CommunicationModule.class).commList(10, -1);
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_interflow;
    }


    @Override
    protected void dataCallback(int result, Object data) {
        switch (result) {
            case ResultCode.SERVICE.COMM_USER:
                //用户信息
                userInfo = (UserInfoBean) data;
                adapterUser(userInfo);
                break;
        }
    }
}
