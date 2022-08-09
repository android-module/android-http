package com.caldremch.http.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *
 * @author Caldremch
 * @date 2019/2/23
 * @Email caldremch@163.com
 * @describe
 *
 **/
public class HttpUtils {

    /**
     *  Callback<T> 获取T
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Type getType(T t){

        //获取直接超类的Type
        Type genericSuperclassType = t.getClass().getGenericSuperclass();

        //获取所有具体类型
        Type[] params = ((ParameterizedType)genericSuperclassType).getActualTypeArguments();

        //通常我们只是一类型, 所有去0
        Type type = params[0];

        Type finalType;

        if (params.length > 1){

            /**
             * 这句话是 type 是否为参数化类型, 为了全面, 我们通常解析对象为 BaseRespData<T>类型, 所以这里不会报错
             *
             */
            if (!(type instanceof ParameterizedType)){
                throw new IllegalStateException("没有填写泛型参数");
            }

            finalType = ((ParameterizedType) type).getActualTypeArguments()[0];
        }else{
            finalType = type;
        }

        return finalType;
    }

}
