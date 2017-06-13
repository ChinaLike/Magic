package com.kelee.frame.temp;

/**
 * Created by kelee on 2017-06-01.
 * 界面加载失败、空、加载中、成功等的回调
 */

public interface ITempView {
    /**
     * 加载错误
     */
    int ERROR = 0xaff1;
    /**
     * 数据为空
     */
    int DATA_NULL = 0xaff2;
    /**
     * 加载中
     */
    int LOADING = 0xaff3;

    /**
     * 设置填充界面类型
     *
     * @param type {@link ITempView#ERROR}
     *             {@link ITempView#DATA_NULL}
     *             {@link ITempView#LOADING}
     */
    void setType(int type);

    /**
     * 在这处理type = ITempView#ERROR 时的逻辑
     */
    void onError();

    /**
     * 在这处理type = ITempView#DATA_NULL 时的逻辑
     */
    void onNull();

    /**
     * 在这处理type = ITempView#LOADING 时的逻辑
     */
    void onLoading();
}
