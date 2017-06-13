package com.kelee.frame.module.inf;

/**
 * Created by kelee on 2017-06-02.
 * module的监听
 */

public interface ModuleListener {

    /**
     * 统一接口的回调，回调接口为dataCallback
     *
     * @param result 返回码
     * @param data   回调数据
     */
    void callback(int result, Object data);
}
