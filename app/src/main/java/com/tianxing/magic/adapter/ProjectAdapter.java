package com.tianxing.magic.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.tianxing.magic.R;
import com.tianxing.magic.entity.order.ProjectBean;
import com.tianxing.magic.help.GlideSetting;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by kelee on 2017-06-12.
 */

public class ProjectAdapter extends CommonAdapter<ProjectBean> {


    public ProjectAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ProjectBean projectBean, int position) {
        GlideSetting.loading((ImageView) holder.getView(R.id.project_img),projectBean.getHeadImage());
        holder.setText(R.id.project_name_a,projectBean.getItemA());
        if (projectBean.isBProject()){

        }
    }

    public void notifyDataSetChanged(List<ProjectBean> list) {
        mDatas  = list;
        notifyDataSetChanged();
    }

}
