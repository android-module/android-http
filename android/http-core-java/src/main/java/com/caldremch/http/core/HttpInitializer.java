package com.caldremch.http.core;

import com.caldremch.http.core.abs.ICommonRequestEventCallback;
import com.caldremch.http.core.abs.IConvert;
import com.caldremch.http.core.abs.IConvertStrategy;
import com.caldremch.http.core.abs.IHeader;
import com.caldremch.http.core.abs.IServerUrlConfig;
import com.caldremch.http.core.framework.base.IGetExecute;
import com.caldremch.http.core.framework.base.IPostExecute;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by Leon on 2022/10/30.
 */
public class HttpInitializer {
    private static Class<? extends IGetExecute> sClzIGetExecute;
    private static Class<? extends IPostExecute> sClzIPostExecute;
    private static Class<? extends IConvert> sClzIConvert;

    public static IGetExecute getGetGetExecute(){
        return getInstanceFactoryOf(sClzIGetExecute);
    }

    public static IPostExecute getPostExecute(){
        return getInstanceFactoryOf(sClzIPostExecute);
    }

    public static IConvert getConvert(){
        return getInstanceFactoryOf(sClzIConvert);
    }

    public static void registerIGetExecute(Class<? extends IGetExecute> clzIGetExecute) {
        sClzIGetExecute = clzIGetExecute;
    }

    public static void registerIPostExecute(Class<? extends IPostExecute> clzClzIPostExecute) {
        sClzIPostExecute = clzClzIPostExecute;
    }

    public static void registerIConvert(Class<? extends IConvert> clzClzIConvert) {
        sClzIConvert = clzClzIConvert;
    }

    private static class InitData {
        private Class<? extends IHeader> headerClz;
        private Class<? extends ICommonRequestEventCallback> requestEventClz;
        private Class<? extends IConvertStrategy> convertStrategyClz;
        private Class<? extends IServerUrlConfig> serverUrlConfigClz;

        public void neededCheck() {

            if (headerClz == null) {
                throw new NullPointerException("IHeader is needed");
            }

            if (requestEventClz == null) {
                throw new NullPointerException("ICommonRequestEventCallback is needed");
            }

            if (convertStrategyClz == null) {
                throw new NullPointerException("IConvertStrategy is needed");
            }

            if (serverUrlConfigClz == null) {
                throw new NullPointerException("IServerUrlConfig is needed");
            }
        }
    }

    private static InitData sInitData;


    private final static HashMap<String, Object> cache = new HashMap<>();

    public static @NotNull IHeader getHeader() {
        return getInstanceSingleOf(sInitData.headerClz);
    }

    public static @NotNull ICommonRequestEventCallback getRequestEventCallback() {
        return getInstanceSingleOf(sInitData.requestEventClz);
    }

    public static @NotNull IConvertStrategy getConvertStrategy() {
        return getInstanceFactoryOf(sInitData.convertStrategyClz);
    }

    public static @NotNull IServerUrlConfig getServerUrlConfig() {
        return getInstanceFactoryOf(sInitData.serverUrlConfigClz);
    }

    @SuppressWarnings({"deprecation"})
    private static <T> T getInstanceFactoryOf(Class<?> clz) {
        try {
            return (T) clz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({"deprecation"})
    private static <T> T getInstanceSingleOf(@NotNull Class<?> clz) {
        try {
            String key = clz.getName();
            Object instance = cache.get(clz.getName());
            if (instance == null) {
                instance = clz.newInstance();
                cache.put(key, instance);
            }
            return (T) instance;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static class Builder {
        private InitData initData;

        public Builder() {
            this.initData = new InitData();
        }

        public Builder registerHeader(Class<? extends IHeader> headerClz) {
            initData.headerClz = headerClz;
            return this;
        }

        public Builder registerRequestEventCallback(Class<? extends ICommonRequestEventCallback> requestEventClz) {
            initData.requestEventClz = requestEventClz;
            return this;
        }

        public Builder registerConvertStrategy(Class<? extends IConvertStrategy> convertStrategyClz) {
            initData.convertStrategyClz = convertStrategyClz;
            return this;
        }

        public Builder registerServerUrlConfig(Class<? extends IServerUrlConfig> serverUrlConfigClz) {
            initData.serverUrlConfigClz = serverUrlConfigClz;
            return this;
        }

        public void build() {
            initData.neededCheck();
            sInitData = initData;
        }
    }

}
