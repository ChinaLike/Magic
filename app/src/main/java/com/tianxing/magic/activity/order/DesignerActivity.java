package com.tianxing.magic.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kelee.frame.temp.TempView;
import com.tianxing.magic.R;
import com.tianxing.magic.base.BaseActivity;
import com.tianxing.magic.config.Constance;
import com.tianxing.magic.config.ResultCode;
import com.tianxing.magic.databinding.ActivityDesignerBinding;
import com.tianxing.magic.databinding.ActivityHairdresserBinding;
import com.tianxing.magic.entity.HairDresserBean;
import com.tianxing.magic.entity.StoreChooseBean;
import com.tianxing.magic.help.GlideSetting;
import com.tianxing.magic.module.OrderModule;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelee on 2017-06-08.
 * 发型师列表
 */

public class DesignerActivity extends BaseActivity<ActivityDesignerBinding> implements MultiItemTypeAdapter.OnItemClickListener {


    private List<HairDresserBean> mList = new ArrayList<>();
    /**
     * 店铺信息
     */
    private StoreChooseBean storeInfo;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_designer;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        showTempView(TempView.LOADING);
        settingRecyclerView(getBinding().hairdresserRecycler);
        getModule(OrderModule.class).hairdresserChoose(getChildStoreId());
    }

    /**
     * 获取分店ID
     *
     * @return
     */
    private String getChildStoreId() {
        storeInfo = getShopInfo().getStroe();
        return storeInfo.getFdBranchID();
    }

    /**
     * 数据适配
     *
     * @param list
     */
    private void adapter(List<HairDresserBean> list) {
        mList = list;
        CommonAdapter adapter = new CommonAdapter<HairDresserBean>(this, R.layout.item_store_choose, list) {
            @Override
            protected void convert(ViewHolder holder, HairDresserBean bean, int position) {
                holder.setText(R.id.store_name, bean.getDesignerName());
                holder.setText(R.id.store_phone, bean.getWorkID());
                holder.setText(R.id.store_address, bean.getMobile());
                GlideSetting.loading((ImageView) holder.getView(R.id.store_img), bean.getHeadImage());
            }
        };
        getBinding().hairdresserRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }


    @Override
    protected void dataCallback(int result, Object data) {
        super.dataCallback(result, data);
        if (result == ResultCode.SERVICE.HAIRDRESSER_LIST) {
            hintTempView();
            adapter((List<HairDresserBean>) data);
        }
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        Intent intent = new Intent(DesignerActivity.this, ProjectActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(Constance.KEY.HAIRDRESSER_INFO, mList.get(position));
//        bundle.putSerializable(Constance.KEY.STROE_INFO, storeInfo);
//        intent.putExtras(bundle);
        getShopInfo().setDresser(mList.get(position));
        intent.putExtra(Constance.KEY.SHOP_INFO, getShopInfo());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
