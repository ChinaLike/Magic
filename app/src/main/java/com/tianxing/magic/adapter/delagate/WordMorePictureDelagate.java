package com.tianxing.magic.adapter.delagate;

import com.tianxing.magic.entity.communication.CommBean;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by kelee on 2017-06-13.
 * 文字图片（多图）排版
 */

public class WordMorePictureDelagate implements ItemViewDelegate<CommBean>{
    @Override
    public int getItemViewLayoutId() {
        return 0;
    }

    @Override
    public boolean isForViewType(CommBean item, int position) {
        return false;
    }

    @Override
    public void convert(ViewHolder holder, CommBean commBean, int position) {

    }
}
