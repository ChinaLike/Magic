package com.tianxing.magic.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kelee.frame.core.AbsActivity;
import com.kelee.frame.temp.TempView;
import com.kelee.frame.util.DensityUtils;
import com.kelee.frame.util.T;
import com.kelee.ui.widget.RecycleViewDivider;
import com.tianxing.magic.R;
import com.tianxing.magic.config.Constance;
import com.tianxing.magic.config.ResultCode;
import com.tianxing.magic.entity.info.ShopInfo;
import com.tianxing.magic.widget.MyToolBar;

import java.util.List;

/**
 * Created by kelee on 2017-06-05.
 */

public abstract class BaseActivity<VB extends ViewDataBinding> extends AbsActivity<VB> {

    protected MyToolBar toolBar;


//    /**
//     * 使用缺省动画
//     */
//    private boolean useDefaultAnim = true;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        if (AndroidVersionUtil.hasLollipop()){
//            if (useDefaultAnim){
//                getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//                getWindow().setExitTransition(new Slide(Gravity.LEFT));
//                getWindow().setEnterTransition(new Slide(Gravity.RIGHT));
//            }
//        }
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void startActivity(Intent intent, @Nullable Bundle options) {
//        if (AndroidVersionUtil.hasLollipop()){
//            options = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
//            super.startActivity(intent, options);
//        }else {
//            super.startActivity(intent, options);
//            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
//        }
//
//    }
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
////        super.startActivityForResult(intent, requestCode, options);
//        if (AndroidVersionUtil.hasLollipop()) {
//            if (options == null) {
//                options = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
//            }
//            super.startActivityForResult(intent, requestCode, options);
//        } else {
//            super.startActivityForResult(intent, requestCode, options);
//            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
//        }
//    }
//
//    protected void setUseDefaultAnim(boolean useDefaultAnim) {
//        this.useDefaultAnim = useDefaultAnim;
//    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        toolBar = (MyToolBar) findViewById(R.id.toolbar);
        if (toolBar != null) {
            toolBar.setLeftListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

    }

    /**
     * 设置RecyclerView的排列方式（垂直排列），分割线（1dp），分隔线颜色（#E7E7E7）
     *
     * @param recyclerView
     */
    protected void settingRecyclerView(RecyclerView recyclerView) {
        settingRecyclerView(recyclerView, true);
    }

    /**
     * 设置RecyclerView的排列方式（垂直排列），分割线（1dp），分隔线颜色（#E7E7E7）
     *
     * @param recyclerView
     * @param isScrollVertically 是否可以垂直滚动
     */
    protected void settingRecyclerView(RecyclerView recyclerView, final boolean isScrollVertically) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                if (!isScrollVertically) {
                    return false;
                }
                return super.canScrollVertically();
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, DensityUtils.dp2px(1),
                ContextCompat.getColor(this, R.color.divider)));
    }

    /**
     * 获取店铺信息
     *
     * @return
     */
    protected ShopInfo getShopInfo() {
        ShopInfo info = (ShopInfo) getIntent().getSerializableExtra(Constance.KEY.SHOP_INFO);
        if (info != null) {
            return info;
        }
        return new ShopInfo();
    }

    @Override
    protected void dataCallback(int result, Object data) {
        if (result == ResultCode.SERVICE.FAILER) {
            T.showShort(this, data.toString());
        } else {
            if (data == null) {
                showTempView(TempView.DATA_NULL);
                return;
            } else if (data instanceof List) {
                List list = (List) data;
                if (list.size() == 0) {
                    showTempView(TempView.DATA_NULL);
                    return;
                } else {
                    hintTempView();
                }
            } else {
                hintTempView();
            }
        }
    }
}
