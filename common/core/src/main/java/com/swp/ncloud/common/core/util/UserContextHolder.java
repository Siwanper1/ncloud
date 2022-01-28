package com.swp.ncloud.common.core.util;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

public class UserContextHolder {

    private ThreadLocal<Map<String, String>> threadLocal;

    private UserContextHolder() {
        this.threadLocal = new ThreadLocal<Map<String, String>>();
    }

    public static UserContextHolder getInstance(){
        return SingleHolder.sInstance;
    }

    private static class SingleHolder {
        private static final UserContextHolder sInstance = new UserContextHolder();
    }

    public void setContext(Map<String, String> map) {
        this.threadLocal.set(map);
    }

    public Map<String, String > getContext(){
        return this.threadLocal.get();
    }

    public String getUserName(){
        return Optional.ofNullable(this.threadLocal.get()).orElse(Maps.<String, String>newHashMap()).get("user_name");
    }

}
