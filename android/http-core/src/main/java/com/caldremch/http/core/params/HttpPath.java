package com.caldremch.http.core.params;

import java.util.LinkedHashMap;

/**
 * Created by Leon on 2022/10/30.
 */
public class HttpPath {
    private LinkedHashMap<String, String> urlParamsMap;

    public LinkedHashMap<String, String> getUrlParams() {
        return this.urlParamsMap;
    }

    public HttpPath() {
        init();
    }

    private void init() {
        this.urlParamsMap = new LinkedHashMap<>();
    }

    public void put(String key, String value) {
        this.urlParamsMap.put(key, value);
    }

    public boolean isEmpty() {
        return this.urlParamsMap.isEmpty();
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().equals("");
    }

    public String getPathUrl(String url) {
        if (!this.urlParamsMap.isEmpty()) {
            for (String key : this.urlParamsMap.keySet()) {
                String value = this.urlParamsMap.get(key);
                if (!isEmpty(value)) {
                    url = url.replace("{" + key + "}", value);
                }
            }
        }
        return url;
    }
}
