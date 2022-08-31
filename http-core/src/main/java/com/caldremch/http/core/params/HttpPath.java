package com.caldremch.http.core.params;

import java.util.LinkedHashMap;

/**
 * @author Caldremch
 * @date 2019-08-20 15:54
 * @email caldremch@163.com
 * @describe
 **/
public class HttpPath {

    private LinkedHashMap<String, String> urlParamsMap;

    public LinkedHashMap<String, String> getUrlParams() {
        return urlParamsMap;
    }

    public HttpPath() {
        init();
    }

    private void init() {
        urlParamsMap = new LinkedHashMap<>();
    }

    public void put(String key, String value) {
        urlParamsMap.put(key, value);
    }

    public boolean isEmpty() {
        return urlParamsMap.isEmpty();
    }

    private  boolean isEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().equals("");
    }

    public String getPathUrl(String url) {
        if (!urlParamsMap.isEmpty()) {
            for (String key : urlParamsMap.keySet()) {
                String value = urlParamsMap.get(key);
                if (!isEmpty(value)){
                    url =  url.replace("{"+key+"}", value);
                }
            }
        }
        return url;
    }

//    public static void main(String[] args){
//        HttpPath httpPath = new HttpPath();
//        httpPath.put("mypath", 1+"");
//        httpPath.put("path2", "gogetit");
//        String url = "/a/b/c/{mypath}/{path2}";
//        System.out.println(httpPath.getPathUrl(url));
//    }

}
