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
import com.tianxing.magic.databinding.ActivityOrderBinding;
import com.tianxing.magic.databinding.ActivitySubbranchBinding;
import com.tianxing.magic.entity.StoreChooseBean;
import com.tianxing.magic.help.GlideSetting;
import com.tianxing.magic.module.OrderModule;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelee on 2017-06-06.
 * 分店选择
 */

public class SubbranchActivity extends BaseActivity<ActivitySubbranchBinding> implements MultiItemTypeAdapter.OnItemClickListener {

    private List<StoreChooseBean> mList = new ArrayList<>();

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        showTempView(TempView.LOADING);
        settingRecyclerView(getBinding().storeChooseRecycler);
        getModule(OrderModule.class).storeChoose(getShopInfo().getShopId());
    }

    /**
     * 数据适配
     *
     * @param list
     */
    private void adapter(List<StoreChooseBean> list) {
        mList = list;
        CommonAdapter adapter = new CommonAdapter<StoreChooseBean>(this, R.layout.item_store_choose, list) {
            @Override
            protected void convert(ViewHolder holder, StoreChooseBean bean, int position) {
                holder.setText(R.id.store_name, bean.getFdName());
                holder.setText(R.id.store_phone, bean.getFdTel());
                holder.setText(R.id.store_address, bean.getFdAddr());
                GlideSetting.loading((ImageView) holder.getView(R.id.store_img), bean.getFdPicURL());
            }
        };
        getBinding().storeChooseRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_subbranch;
    }

    @Override
    protected void dataCallback(int result, Object data) {
        super.dataCallback(result, data);
        hintTempView();
        if (result == ResultCode.SERVICE.STROE_LIST) {
            adapter((List<StoreChooseBean>) data);
        }
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        Intent intent = new Intent(SubbranchActivity.this, DesignerActivity.class);
        getShopInfo().setStroe(mList.get(position));
        intent.putExtra(Constance.KEY.SHOP_INFO, getShopInfo());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
