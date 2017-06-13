package com.tianxing.magic.activity.user;

import com.tianxing.magic.R;
import com.tianxing.magic.base.BaseActivity;
import com.tianxing.magic.databinding.ActivityPersonalCenterBinding;

/**
 * Created by kelee on 2017-06-06.
 * 个人中心
 */

public class PersonalCenterActivity extends BaseActivity<ActivityPersonalCenterBinding>{
    @Override
    protected int setLayoutId() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected void dataCallback(int result, Object data) {

    }
}
