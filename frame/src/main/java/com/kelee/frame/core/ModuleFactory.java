package com.kelee.frame.core;

import android.content.Context;

import com.kelee.frame.module.AbsModule;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kelee on 2017-06-03.
 * Module的共享工厂
 */

public class ModuleFactory {

    private Map<Integer, AbsModule> mModules = new ConcurrentHashMap<>();

    private ModuleFactory() {
    }

    public static ModuleFactory newInstance() {
        return new ModuleFactory();
    }

    /**
     * 获取Module
     */
    public <M extends AbsModule> M getModule(Context context, Class<M> clazz) {
        M module = (M) mModules.get(clazz.hashCode());
        if (module == null) {
            return newInstanceModule(context, clazz);
        }
        return module;
    }

    /**
     * 构造一个新的Module
     */
    private <T extends AbsModule> T newInstanceModule(Context context, Class<T> clazz) {
        Class[] paramTypes = { Context.class };
        Object[] params = { context };
        try {
            Constructor<T> con = clazz.getConstructor(paramTypes);
            con.setAccessible(true);
            T module = con.newInstance(params);
            mModules.put(clazz.hashCode(), module);
            return module;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
