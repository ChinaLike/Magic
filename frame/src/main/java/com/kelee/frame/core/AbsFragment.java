package com.kelee.frame.core;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kelee.frame.module.AbsModule;
import com.kelee.frame.module.IOCProxy;
import com.kelee.frame.temp.AbsTempView;
import com.kelee.frame.temp.OnTempBtClickListener;
import com.kelee.frame.temp.TempView;
import com.kelee.frame.util.L;
import com.kelee.frame.util.ReflectionUtil;
import com.kelee.frame.util.StringUtil;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Created by kelee on 2017-06-01.
 * 基础Fragment
 */

public abstract class AbsFragment<VB extends ViewDataBinding> extends Fragment implements OnTempBtClickListener{
    protected String TAG = "";
    protected View mRootView;
    protected AbsActivity mActivity;
    protected boolean isInit;
    private VB mBind;
    private ViewGroup mParent;

    protected AbsTempView mTempView;
    protected boolean useTempView = true;

    private IOCProxy mProxy;
    private ModuleFactory mModuleFactory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBind = DataBindingUtil.inflate(inflater,setLayoutId(),container,false);
        initFragment();
        mRootView = mBind.getRoot();
        return mRootView;
    }

    protected void initFragment(){
        TAG = StringUtil.getClassName(this);
        mProxy = IOCProxy.newInstance(this);
        mModuleFactory = ModuleFactory.newInstance();
        ButterKnife.bind(this,mBind.getRoot());
        if (useTempView){
            mTempView = new TempView(getContext());
            mTempView.setBtListener(this);
        }
    }

    protected abstract int setLayoutId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AbsActivity){
            mActivity = (AbsActivity) context;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Field field = ReflectionUtil.getField(getClass(), "mContainerId");
        Field containerField = ReflectionUtil.getField(getFragmentManager().getClass(), "mContainer");
        try {
            int id = (int) field.get(this);
            FragmentContainer container = (FragmentContainer) containerField.get(getFragmentManager());
            mParent = (ViewGroup) container.onFindViewById(id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        init(savedInstanceState);
        isInit = true;
        if (getUserVisibleHint()) {
            onDelayLoad();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Field field = ReflectionUtil.getField(getClass(), "mContainer");
        try {
            mParent = (ViewGroup) field.get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isInit) {
            isInit = false;
            onDelayLoad();
        }
    }

    protected abstract void init(Bundle savedInstanceState);

    /**
     * 获取binding对象
     */
    protected VB getBinding() {
        return mBind;
    }

    /**
     * 延时加载
     */
    protected abstract void onDelayLoad();

    /**
     * 获取Module
     *
     * @param clazz {@link AbsModule}
     */
    protected <M extends AbsModule> M getModule(@NonNull Class<M> clazz) {
        M module = mModuleFactory.getModule(getContext(), clazz);
        module.setHost(this);
        mProxy.changeModule(module);
        return module;
    }

    /**
     * 获取Module
     *
     * @param clazz Module class0
     * @param callback Module回调函数
     * @param <M> {@link AbsModule}
     */
    protected <M extends AbsModule> M getModule(@NonNull Class<M> clazz,
                                                @NonNull AbsModule.OnCallback callback) {
        M module = mModuleFactory.getModule(getContext(), clazz);
        module.setCallback(callback);
        mProxy.changeModule(module);
        return module;
    }
    /**
     * 数据回调
     */
    protected abstract void dataCallback(int result, Object obj);

    /**
     * 是否使用填充界面
     * @param useTempView
     */
    public void setUseTempView(boolean useTempView) {
        this.useTempView = useTempView;
    }

    /**
     * 设置自定义的TempView
     */
    protected void setCustomTempView(AbsTempView tempView) {
        mTempView = tempView;
        mTempView.setBtListener(this);
    }

    /**
     * 显示占位布局
     *
     * @param type {@link TempView#ERROR}
     * {@link TempView#DATA_NULL}
     * {@link TempView#LOADING}
     */
    protected void showTempView(int type) {
        if (mTempView == null || !useTempView) {
            return;
        }
        mTempView.setType(type);
        if (mParent != null) {
            int size = ViewGroup.LayoutParams.MATCH_PARENT;
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(size, size);
            if (mParent instanceof ViewPager) {
                //                ViewPager vp = (ViewPager) mParent;
                //                int position = vp.getCurrentItem();
                //                View child = vp.getChildAt(position);
                //                L.d(TAG, "hashcode ==> " + child.hashCode());
                View child = mRootView;
                if (child != null) {
                    if (child instanceof LinearLayout) {
                        LinearLayout ll = (LinearLayout) child;
                        ll.removeView(mTempView);
                        ll.addView(mTempView, 0, lp);
                    } else if (child instanceof RelativeLayout || child instanceof FrameLayout) {
                        ViewGroup vg = (ViewGroup) child;
                        vg.removeView(mTempView);
                        vg.addView(mTempView, lp);
                    } else {
                        L.e(TAG, "框架的填充只支持，LinearLayout、RelativeLayout、FrameLayout");
                    }
                }
            } else {
                mParent.removeView(mTempView);
                mParent.addView(mTempView, lp);
            }
        }
    }

    /**
     * 关闭占位布局
     */
    protected void hintTempView() {
        hintTempView(0);
    }

    /**
     * 延时关闭占位布局
     */
    protected void hintTempView(int delay) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                if (mTempView == null || !useTempView) {
                    return;
                }
                mTempView.clearFocus();
                if (mParent != null) {
                    if (mParent instanceof ViewPager) {
                        //                        ViewPager vp = (ViewPager) mParent;
                        //                        int position = vp.getCurrentItem();
                        //                        View child = vp.getChildAt(position);
                        View child = mRootView;
                        ViewGroup vg = (ViewGroup) child;
                        if (vg != null) {
                            vg.removeView(mTempView);
                        }
                    } else {
                        mParent.removeView(mTempView);
                    }
                }
            }
        }, delay);
    }

    @Override public void onBtTempClick(View view, int type) {
    }

    /**
     * 获取填充View
     */
    protected AbsTempView getTempView() {
        return mTempView;
    }
}
