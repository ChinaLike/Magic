package com.tianxing.magic.activity.recommend;

import com.tianxing.magic.R;
import com.tianxing.magic.base.BaseActivity;
import com.tianxing.magic.databinding.ActivityHairStyleRecommendBinding;

/**
 * Created by kelee on 2017-06-06.
 * 发型推荐
 */

public class HairStyleRecommendActivity extends BaseActivity<ActivityHairStyleRecommendBinding> {
    @Override
    protected int setLayoutId() {
        return R.layout.activity_hair_style_recommend;
    }

    @Override
    protected void dataCallback(int result, Object data) {
    }
}
