package com.tianxing.magic.activity.communication;

import android.os.Bundle;

import com.tianxing.magic.R;
import com.tianxing.magic.base.BaseActivity;
import com.tianxing.magic.databinding.ActivityInterflowBinding;

/**
 * Created by kelee on 2017-06-06.
 * 交流圈
 */

public class InterflowActivity extends BaseActivity<ActivityInterflowBinding> {

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_interflow;
    }

    @Override
    protected void dataCallback(int result, Object data) {

    }
}
