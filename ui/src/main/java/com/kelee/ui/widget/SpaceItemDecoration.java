package com.kelee.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kelee on 2017-06-09.
 * 设置Item的水平和处置间隔
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int left = 0;
    private int top = 0;
    private int right = 0;
    private int bottom = 0;

    public SpaceItemDecoration(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildCount() != 0) {
            outRect.left = left;
            outRect.top = top;
            outRect.right = right;
            outRect.bottom = bottom;
        }
    }
}
