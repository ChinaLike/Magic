package com.tianxing.magic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tianxing.magic.R;
import com.tianxing.magic.activity.activities.StoreActivityActivity;
import com.tianxing.magic.activity.communication.InterflowActivity;
import com.tianxing.magic.activity.order.SubbranchActivity;
import com.tianxing.magic.activity.recommend.HairStyleRecommendActivity;
import com.tianxing.magic.activity.shop.StoreInfoActivity;
import com.tianxing.magic.activity.user.PersonalCenterActivity;
import com.tianxing.magic.base.BaseActivity;
import com.tianxing.magic.config.Constance;
import com.tianxing.magic.config.ResultCode;
import com.tianxing.magic.databinding.ActivityMainBinding;
import com.tianxing.magic.entity.BannerBean;
import com.tianxing.magic.help.GlideImageLoader;
import com.tianxing.magic.module.MainModule;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;

/**
 * 主页界面
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        getModule(MainModule.class).banner(getShopInfo().getShopId());
    }

    /**
     * 初始化Banner
     *
     * @param bean
     */
    private void initBanner(List<BannerBean> bean) {
        Banner banner = getBinding().banner;
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(bean);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void dataCallback(int result, Object data) {
        switch (result) {
            case ResultCode.SERVICE.BANNER_LIST:
                initBanner((List<BannerBean>) data);
                break;
        }
    }

    /**
     * 点击事件绑定
     *
     * @param view
     */
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.order_parent:
                //美发预约
                intent = new Intent(MainActivity.this, SubbranchActivity.class);
                intent.putExtra(Constance.KEY.SHOP_INFO, getShopInfo());
                startActivity(intent);
                break;
            case R.id.exchange_parent:
                //交流圈
                intent = new Intent(MainActivity.this, InterflowActivity.class);
                startActivity(intent);
                break;
            case R.id.recommended:
                //发型推荐
                intent = new Intent(MainActivity.this, HairStyleRecommendActivity.class);
                startActivity(intent);
                break;
            case R.id.store_parent:
                //门店信息
                intent = new Intent(MainActivity.this, StoreInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.personal_parent:
                //个人中心
                intent = new Intent(MainActivity.this, PersonalCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.activities_parent:
                //店铺活动
                intent = new Intent(MainActivity.this, StoreActivityActivity.class);
                startActivity(intent);
                break;
        }
    }


}
