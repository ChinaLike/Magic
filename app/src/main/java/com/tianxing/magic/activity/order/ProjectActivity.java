package com.tianxing.magic.activity.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kelee.frame.temp.TempView;
import com.kelee.frame.util.T;
import com.tianxing.magic.R;
import com.tianxing.magic.base.BaseActivity;
import com.tianxing.magic.config.Constance;
import com.tianxing.magic.config.ResultCode;
import com.tianxing.magic.databinding.ActivityProjectChooseBinding;
import com.tianxing.magic.entity.order.PriceBean;
import com.tianxing.magic.entity.order.ProjectBean;
import com.tianxing.magic.help.GlideSetting;
import com.tianxing.magic.module.OrderModule;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kelee on 2017-06-08.
 * 项目选择
 */

public class ProjectActivity extends BaseActivity<ActivityProjectChooseBinding> {

    private Map<Integer, ProjectBean> mMap = new HashMap<>();

    /**
     * 列表中Button按钮集合
     */
    private Map<Integer, ViewHolder> views = new HashMap<>();
    /**
     * 项目列表适配器
     */
    private CommonAdapter adapter;
    /**
     * 一行个数
     */
    private final int ROW_NUM = 4;

    private Map<Integer, Map<Integer, ViewHolder>> aHolder = new HashMap<>();

    private Map<Integer, Map<Integer, ViewHolder>> bHolder = new HashMap<>();

    private Map<Integer, Map<Integer, PriceBean>> aPrice = new HashMap<>();

    private Map<Integer, Map<Integer, PriceBean>> bPrice = new HashMap<>();
    /**
     * location[0]A项目，location[1]B项目，location[2]最新点击坐标
     */
    private int[][] location = {{-1, -1}, {-1, -1}, {-1, -1}};

    /**
     * 确定按钮被选中
     */
    private int confirePosition = -1;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_project_choose;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        showTempView(TempView.LOADING);
        settingRecyclerView(getBinding().projectRecycler);
//        getModule(OrderModule.class).projectChoose(getChildStoreId(), getHairdresserId());
        // TODO: 2017/6/8/008 修改数据 ，有待商量
        getModule(OrderModule.class).projectChoose(getShopInfo().getShopId(), getShopInfo().getDesigner().getDesignerID());
    }

    /**
     * 项目列表数据适配
     *
     * @param list
     */
    private void adapter(List<ProjectBean> list) {

        adapter = new CommonAdapter<ProjectBean>(this, R.layout.item_project_choose, list) {
            @Override
            protected void convert(ViewHolder holder, ProjectBean bean, int position) {
                views.put(position, holder);
                mMap.put(position, bean);
                bean.setPosition(position);
                GlideSetting.loading((ImageView) holder.getView(R.id.project_img), bean.getHeadImage());
                if (!TextUtils.isEmpty(bean.getItemA())) {
                    bean.setPriceAList(bean.getPriceA());
                    holder.setVisible(R.id.project_a, true);
                    holder.setText(R.id.project_name_a, bean.getItemA());
                    childAAdapter((RecyclerView) holder.getView(R.id.project_recycler_a), bean);
                } else {
                    holder.setVisible(R.id.project_a, false);
                }
                if (!TextUtils.isEmpty(bean.getItemB())) {
                    bean.setPriceBList(bean.getPriceB());
                    holder.setVisible(R.id.project_b, true);
                    holder.setText(R.id.project_name_b, bean.getItemB());
                    childBAdapter((RecyclerView) holder.getView(R.id.project_recycler_b), bean);
                } else {
                    holder.setVisible(R.id.project_b, false);
                }
                if (confirePosition == position) {
                    holder.getView(R.id.project_confirm).setSelected(true);
                } else {
                    holder.getView(R.id.project_confirm).setSelected(false);
                }
                btnClick((Button) holder.getView(R.id.project_confirm), bean);
            }
        };
        getBinding().projectRecycler.setAdapter(adapter);

    }

    /**
     * 确认按钮被点击，处理按钮背景变换、跳转
     *
     * @param btn
     */
    private void btnClick(final Button btn, final ProjectBean bean) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn.isSelected()) {
                    Intent intent = new Intent(ProjectActivity.this, TimeActivity.class);
                    getShopInfo().setProject(bean);
                    if (location[0][0] != -1 && location[0][1] != -1) {
                        getShopInfo().setPriceA(aPrice.get(location[0][0]).get(location[0][1]));
                    }
                    if (location[1][0] != -1 && location[1][1] != -1) {
                        getShopInfo().setPriceB(bPrice.get(location[1][0]).get(location[1][1]));
                    }
                    intent.putExtra(Constance.KEY.SHOP_INFO, getShopInfo());
                    startActivity(intent);
                } else {
                    T.showShort(ProjectActivity.this, "亲，请选择完后提交！");
                }
            }
        });
    }

    /**
     * 设置项目A
     *
     * @param view
     * @param bean
     */
    private void childAAdapter(RecyclerView view, final ProjectBean bean) {
        final Map<Integer, ViewHolder> childA = new HashMap<>();
        final Map<Integer, PriceBean> beanA = new HashMap<>();
        CommonAdapter adapter = new CommonAdapter<PriceBean>(this, R.layout.item_project_price, bean.getPriceAList()) {
            @Override
            protected void convert(ViewHolder holder, PriceBean o, final int position) {
                settingBaseParams(holder, o);
                beanA.put(position, o);
                aPrice.put(bean.getPosition(), beanA);
                childA.put(position, holder);
                aHolder.put(bean.getPosition(), childA);
                if (location[0][0] == bean.getPosition() && location[0][1] == position) {
                    holder.setTextColor(R.id.price, Color.WHITE);
                    holder.getView(R.id.price_parent).setSelected(true);
                } else {
                    holder.setTextColor(R.id.price, ContextCompat.getColor(getApplicationContext(), R.color.project_price_text));
                    holder.getView(R.id.price_parent).setSelected(false);
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        location[2][0] = location[0][0] = bean.getPosition();
                        location[2][1] = location[0][1] = position;
                        setPriceBg(bean.getPosition(), position, aHolder, aPrice);
                    }
                });
            }
        };
        view.setLayoutManager(new GridLayoutManager(this, ROW_NUM));
        view.setAdapter(adapter);
    }

    /**
     * 设置项目B
     *
     * @param view
     * @param bean
     */
    private void childBAdapter(RecyclerView view, final ProjectBean bean) {
        final Map<Integer, ViewHolder> childB = new HashMap<>();
        final Map<Integer, PriceBean> beanB = new HashMap<>();
        CommonAdapter adapter = new CommonAdapter<PriceBean>(this, R.layout.item_project_price, bean.getPriceBList()) {
            @Override
            protected void convert(final ViewHolder holder, final PriceBean o, final int position) {
                settingBaseParams(holder, o);
                beanB.put(position, o);
                bPrice.put(bean.getPosition(), beanB);
                childB.put(position, holder);
                bHolder.put(bean.getPosition(), childB);
                if (location[1][0] == bean.getPosition() && location[1][1] == position) {
                    holder.setTextColor(R.id.price, Color.WHITE);
                    holder.getView(R.id.price_parent).setSelected(true);
                } else {
                    holder.setTextColor(R.id.price, ContextCompat.getColor(getApplicationContext(), R.color.project_price_text));
                    holder.getView(R.id.price_parent).setSelected(false);
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        location[2][0] = location[1][0] = bean.getPosition();
                        location[2][1] = location[1][1] = position;
                        setPriceBg(bean.getPosition(), position, bHolder, bPrice);
                    }
                });
            }
        };
        view.setLayoutManager(new GridLayoutManager(this, ROW_NUM));
        view.setAdapter(adapter);
    }

    /**
     * @param position
     * @param childPosition
     */
    private void setPriceBg(int position, int childPosition, Map<Integer, Map<Integer, ViewHolder>> holder, Map<Integer, Map<Integer, PriceBean>> price) {
        for (int p : holder.keySet()) {
            for (int b : holder.get(p).keySet()) {
                ViewHolder hodler = holder.get(p).get(b);
                PriceBean bean = price.get(p).get(b);
                if (p == position && b == childPosition) {
                    bean.setSelect(true);
                    hodler.getView(R.id.price_parent).setSelected(true);
                    hodler.setTextColor(R.id.price, Color.WHITE);
                } else {
                    bean.setSelect(false);
                    hodler.getView(R.id.price_parent).setSelected(false);
                    hodler.setTextColor(R.id.price, ContextCompat.getColor(getApplicationContext(), R.color.project_price_text));
                }
            }
        }
        changeBtn();
    }

    /**
     * 设置项目基本参数
     *
     * @param holder
     * @param bean
     */
    private void settingBaseParams(ViewHolder holder, PriceBean bean) {
        holder.setText(R.id.price, bean.getPrice() + "元");
        if (bean.isSelect()) {
            holder.getView(R.id.price_parent).setSelected(true);
            holder.setTextColor(R.id.price, Color.WHITE);
        } else {
            holder.getView(R.id.price_parent).setSelected(false);
            holder.setTextColor(R.id.price, ContextCompat.getColor(getApplicationContext(), R.color.project_price_text));
        }
    }

    /**
     * 改变Button的颜色
     */
    private void changeBtn() {
        for (Integer r : views.keySet()) {
            views.get(r).getView(R.id.project_confirm).setSelected(false);
            ProjectBean bean = mMap.get(r);
            bean.setSelect(false);
            if (r == location[2][0]) {
                //选中项目
                if (bean.isDoubleProject() && location[0][0] == location[1][0]) {
                    bean.setSelect(true);
                    confirePosition = location[2][0];
                    adapter.notifyItemChanged(location[0][0], false);
                } else if (bean.isAProject() && location[0][0] == location[2][0]) {
                    bean.setSelect(true);
                    confirePosition = location[2][0];
                    adapter.notifyItemChanged(location[0][0], false);
                } else if (bean.isBProject() && location[1][0] == location[2][0]) {
                    bean.setSelect(true);
                    confirePosition = location[2][0];
                    adapter.notifyItemChanged(location[1][0], false);
                } else {
                    bean.setSelect(false);
                    adapter.notifyItemChanged(r, false);
                }
            }
        }
    }


    @Override
    protected void dataCallback(int result, Object data) {
        super.dataCallback(result, data);
        if (result == ResultCode.SERVICE.PROJECT_LIST) {
            hintTempView();
            adapter((List<ProjectBean>) data);
        }
    }
}