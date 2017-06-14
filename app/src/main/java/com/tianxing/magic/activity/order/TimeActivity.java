package com.tianxing.magic.activity.order;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.kelee.frame.temp.TempView;
import com.kelee.frame.util.CalendarUtils;
import com.kelee.frame.util.DensityUtils;
import com.kelee.frame.util.L;
import com.kelee.frame.util.T;
import com.kelee.ui.widget.SpaceItemDecoration;
import com.tianxing.magic.R;
import com.tianxing.magic.base.BaseActivity;
import com.tianxing.magic.config.ResultCode;
import com.tianxing.magic.databinding.ActivityTimeChooseBinding;
import com.tianxing.magic.entity.order.TimeBean;
import com.tianxing.magic.module.OrderModule;
import com.tianxing.magic.widget.TimeView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kelee on 2017-06-09.
 * 预约时间选择
 */

public class TimeActivity extends BaseActivity<ActivityTimeChooseBinding> implements
        View.OnClickListener, TabLayout.OnTabSelectedListener, MultiItemTypeAdapter.OnItemClickListener {
    /**
     * 日期
     */
    private List<String> mDataList = new ArrayList<>();
    /**
     * 获取的天数
     */
    private static final int DAY = 10;
    /**
     * 两个时间相差的分钟数
     */
    private int timeLag = 0;
    /**
     * 适配器
     */
    private CommonAdapter adapter;
    /**
     * 当前选中项视图
     */
    private View currView = null;
    /**
     * 装载时间
     */
    private List<TimeBean> timeList = new ArrayList<>();
    /**
     * 时间列表
     */
    private List<String> datas = new ArrayList<>();
    /**
     * 当前时间
     */
    private String currTime;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_time_choose;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        showTempView(TempView.LOADING);
        initView();
        toolBar.setRightIcon(R.drawable.time_refresh);
        toolBar.setRightListener(this);
        adapter(timeList);
        initData();
        initTab();
    }

    /**
     * 初始化视图大小
     */
    private void initView() {
        Drawable drawableFree = ContextCompat.getDrawable(this, R.drawable.status_order_free);
        drawableFree.setBounds(0, 0, DensityUtils.sp2px(this, 28), DensityUtils.sp2px(this, 14));//第一0是距左边距离，第二0是距上边距离
        getBinding().timeFree.setCompoundDrawables(drawableFree, null, null, null);//只放左边

        Drawable drawablePart = ContextCompat.getDrawable(this, R.drawable.status_order_part);
        drawablePart.setBounds(0, 0, DensityUtils.sp2px(this, 28), DensityUtils.sp2px(this, 14));//第一0是距左边距离，第二0是距上边距离
        getBinding().timePart.setCompoundDrawables(drawablePart, null, null, null);//只放左边

        Drawable drawableFull = ContextCompat.getDrawable(this, R.drawable.status_order_full);
        drawableFull.setBounds(0, 0, DensityUtils.sp2px(this, 28), DensityUtils.sp2px(this, 14));//第一0是距左边距离，第二0是距上边距离
        getBinding().timeFull.setCompoundDrawables(drawableFull, null, null, null);//只放左边

        Drawable drawableRest = ContextCompat.getDrawable(this, R.drawable.status_order_rest);
        drawableRest.setBounds(0, 0, DensityUtils.sp2px(this, 28), DensityUtils.sp2px(this, 14));//第一0是距左边距离，第二0是距上边距离
        getBinding().timeRest.setCompoundDrawables(drawableRest, null, null, null);//只放左边
    }

    /**
     * 初始化日期
     */
    private void initData() {
        datas = CalendarUtils.getDatas(DAY);
        List<String> weeks = CalendarUtils.getWeeks(DAY);
        for (int i = 0; i < DAY; i++) {
            mDataList.add(datas.get(i) + "\n" + weeks.get(i));
        }
    }

    /**
     * 初始化导航栏
     */
    private void initTab() {
        //添加分割线
//        LinearLayout linearLayout = (LinearLayout) getBinding().timeTab.getChildAt(0);
//        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,R.drawable.tab_divider_vertical));
        getBinding().timeTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        getBinding().timeTab.setOnTabSelectedListener(this);
        for (int i = 0; i < DAY; i++) {
            getBinding().timeTab.addTab(getBinding().timeTab.newTab().setText(mDataList.get(i).substring(5)));
        }
        hintTempView();
    }

    /**
     * 获取时间表
     *
     * @param data
     */
    private void time(String data) {
        getModule(OrderModule.class).timeChoose(getShopInfo().getDesigner().getDesignerID(), data);
        getModule(OrderModule.class).tip(getShopInfo().getDesigner().getDesignerID(), data);
    }

    /**
     * 数据适配
     *
     * @param list
     */
    private void adapter(List<TimeBean> list) {
        adapter = new CommonAdapter<TimeBean>(this, R.layout.item_time, list) {
            @Override
            protected void convert(ViewHolder holder, TimeBean bean, int position) {
                holder.setText(R.id.time, bean.getPartTime());
                if (bean.getRest() == 0) {
                    int useTime = bean.getUsedMins();
                    if (useTime == 0) {
                        //使用时间为0
                        ((TimeView) holder.getView(R.id.line)).drawFree();
                    } else if (useTime > 0 && useTime < timeLag) {
                        //未完全使用完时间
                        ((TimeView) holder.getView(R.id.line)).drawPart(bean.getUsedMins(), timeLag);
                    } else if (useTime > 0 && useTime == timeLag) {
                        //使用完时间
                        ((TimeView) holder.getView(R.id.line)).drawFull();
                    } else {
                        ((TimeView) holder.getView(R.id.line)).drawFree();
                    }
                } else if (bean.getRest() == 1) {
                    //休息
                    ((TimeView) holder.getView(R.id.line)).drawRest();
                } else {
                    ((TimeView) holder.getView(R.id.line)).drawFree();
                }
            }
        };
        adapter.setOnItemClickListener(this);
        getBinding().timeRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        getBinding().timeRecycler.addItemDecoration(
                new SpaceItemDecoration(DensityUtils.dp2px(12), DensityUtils.dp2px(8),
                        DensityUtils.dp2px(12), DensityUtils.dp2px(8)));
        getBinding().timeRecycler.setAdapter(adapter);
    }

    @Override
    protected void dataCallback(int result, Object data) {
        super.dataCallback(result, data);
        switch (result) {
            case ResultCode.SERVICE.TIME_LIST:
                timeList.clear();
                timeList.addAll((List<TimeBean>) data);
                if (timeList.size() > 2) {
                    timeLag = (int) CalendarUtils.computeTimeLag(timeList.get(0).getPartTime(), timeList.get(1).getPartTime());
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        //刷新按钮监听
        if (!TextUtils.isEmpty(currTime)) {
            time(currTime);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        currTime = datas.get(tab.getPosition());
        time(currTime);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        long projectTime = getShopInfo().getProject().getNeedMins();//项目需要时间
        long coutinuousTime = timeList.get(position).getSpareValue();//可使用时间
        if (projectTime > coutinuousTime) {
            //时间不够用
            T.showLastCenterShort(TimeActivity.this, getString(R.string.time_warn));
        } else {
            if (currView != null) {
                currView.setSelected(false);
            }
            view.setSelected(true);
            currView = view;
        }

    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
