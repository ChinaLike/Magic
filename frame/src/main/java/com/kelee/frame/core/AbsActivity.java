package com.kelee.frame.core;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kelee.frame.module.AbsModule;
import com.kelee.frame.module.IOCProxy;
import com.kelee.frame.temp.AbsTempView;
import com.kelee.frame.temp.OnTempBtClickListener;
import com.kelee.frame.temp.TempView;
import com.kelee.frame.util.StringUtil;
import com.kelee.frame.util.T;

import butterknife.ButterKnife;

/**
 * Created by kelee on 2017/6/1/001.
 */

public abstract class AbsActivity<VB extends ViewDataBinding> extends AppCompatActivity
        implements OnTempBtClickListener {

    protected String TAG = "";
    protected AbsFrame mAm;
    protected View mRootView;
    private VB mBind;
    /**
     * 第一次点击返回的系统时间
     */
    private long mFirstClickTime = 0;
    protected AbsTempView mTempView;
    protected boolean useTempView = true;

    private IOCProxy mProxy;
    private ModuleFactory mModuleFactory;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();
        init(savedInstanceState);
    }

    /**
     * Activity加载时实例化一些数据
     */
    private void initialization() {
        mAm = AbsFrame.getInstance();
        mAm.addActivity(this);
        mBind = DataBindingUtil.setContentView(this, setLayoutId());
        mProxy = IOCProxy.newInstance(this);
        mModuleFactory = ModuleFactory.newInstance();
        TAG = StringUtil.getClassName(this);
        ButterKnife.bind(this);
        mRootView = mBind.getRoot();
        if (useTempView) {
            mTempView = new TempView(this);
            mTempView.setBtListener(this);
        }
    }

    /**
     * 设置资源布局
     *
     * @return
     */
    protected abstract int setLayoutId();

    /**
     * 子类继承该类时初始化数据需要实现init方法，相当于AppCompatActivity的onCreate方法
     *
     * @param savedInstanceState
     */
    protected void init(Bundle savedInstanceState) {

    }

    protected ModuleFactory getModuleFactory() {
        return mModuleFactory;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 关闭当前Activity
     */
    @Override
    public void finish() {
        super.finish();
        mAm.removeActivity(this);
    }

    /**
     * 获取当前跟视图
     *
     * @return
     */
    public View getRootView() {
        return mRootView;
    }

    /**
     * 获取Bind对象
     *
     * @return
     */
    protected VB getBinding() {
        return mBind;
    }

    /**
     * 获取填充的View
     *
     * @return
     */
    protected AbsTempView getTempView() {
        return mTempView;
    }

    /**
     * 是否使用填充界面
     *
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
     *             {@link TempView#DATA_NULL}
     *             {@link TempView#LOADING}
     */
    protected void showTempView(int type) {
        if (mTempView == null || !useTempView) {
            return;
        }
        mTempView.setVisibility(View.VISIBLE);
        mTempView.setType(type);
        setContentView(mTempView);
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
            @Override
            public void run() {
                if (mTempView == null || !useTempView) {
                    return;
                }
                mTempView.clearFocus();
                mTempView.setVisibility(View.GONE);
                setContentView(mRootView);
            }
        }, delay);
    }

    /**
     * 获取Module
     *
     * @param clazz {@link AbsModule}
     */
    protected <M extends AbsModule> M getModule(@NonNull Class<M> clazz) {
        M module = mModuleFactory.getModule(this, clazz);
        module.setHost(this);
        mProxy.changeModule(module);
        return module;
    }

    /**
     * 获取Module
     *
     * @param clazz    Module class0
     * @param callback Module回调函数
     * @param <M>      {@link AbsModule}
     */
    protected <M extends AbsModule> M getModule(@NonNull Class<M> clazz,
                                                @NonNull AbsModule.OnCallback callback) {
        M module = mModuleFactory.getModule(this, clazz);
        module.setCallback(callback);
        mProxy.changeModule(module);
        return module;
    }

    @Override
    public void onBtTempClick(View view, int type) {
    }

    /**
     * 数据回调
     */
    protected abstract void dataCallback(int result, Object data);

    /**
     * 双击退出
     */
    private boolean onDoubleClickExit(long timeSpace) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - mFirstClickTime > timeSpace) {
            T.showShort(this, "再按一次退出");
            mFirstClickTime = currentTimeMillis;
            return false;
        } else {
            return true;
        }
    }


    /**
     * 双击退出，间隔时间为2000ms
     */
    public boolean onDoubleClickExit() {
        return onDoubleClickExit(2000);
    }

    /**
     * 退出应用程序
     *
     * @param isBackground 是否开开启后台运行,如果为true则为后台运行
     */
    public void exitApp(Boolean isBackground) {
        mAm.exitApp(isBackground);
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        mAm.exitApp(false);
    }
}
