package com.kelee.frame.module;

import com.kelee.frame.module.inf.ModuleListener;
import com.kelee.frame.util.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by kelee on 2017-06-02.
 * 代理对象
 */

public class IOCProxy implements ModuleListener {

    private static final String TAG = "IOCProxy";
    private static final String mMethod = "dataCallback";

    private Object mObj;

    /**
     * 初始化静态代理
     * @param obj
     * @param module
     * @return
     */
    public static IOCProxy newInstance(Object obj,AbsModule module){
        return new IOCProxy(obj,module);
    }

    public static IOCProxy newInstance(Object obj){
        return new IOCProxy(obj,null);
    }

    /**
     * 被代理对象
     * @param obj
     * @param module
     */
    private IOCProxy(Object obj,AbsModule module){
        this.mObj = obj;
        if (module != null){
            module.setModuleListener(this);
        }
    }

    public void changeModule(AbsModule module){
        module.setModuleListener(this);
    }

    /**
     * 统一的数据回调
     *
     * @param result 返回码
     * @param data 回调数据
     */
    @Override public void callback(int result, Object data) {
        synchronized (this) {
            try {
                Method m = ReflectionUtil.getMethod(mObj.getClass(), mMethod, int.class, Object.class);
                m.setAccessible(true);
                m.invoke(mObj, result, data);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
