package com.somewan.groupcache.demo.service;

import com.alibaba.fastjson.JSON;
import com.somewan.cache.GroupCache;
import com.somewan.cache.getter.LocalGetter;
import com.somewan.cache.getter.LocalGetterDemo;
import com.somewan.cache.result.Result;
import com.somewan.cache.result.ResultCode;

/**
 * Created by wan on 2017/2/3.
 */
public class GroupCacheService {
    GroupCache cache;

    /**
     * 初始化GroupCache
     */
    public void init() {
        String cacheName = "GroupCache";
        String[] peers = {"localhost:10000", "localhost:10001"};

        LocalGetter getter = new LocalGetterDemo();
        cache = new GroupCache(cacheName, getter);
        cache.initPeers(peers[1], peers);
        cache.newNamespace("wan");
        cache.newNamespace("cai");
    }

    private GroupCacheService() {

    }

    private volatile static GroupCacheService instance;

    public static GroupCacheService getInstance() {
        if(instance == null) {
            synchronized (GroupCacheService.class) {
                if(instance == null) {
                    instance = new GroupCacheService();
                }
            }
        }
        return instance;
    }

    public String get(String namespace, String key) {
        Result result = cache.get(namespace, key);
        return JSON.toJSONString(result);
    }

    public String badRequest() {
        Result result = Result.errorResult(ResultCode.BAD_REQUEST);
        return JSON.toJSONString(result);
    }

    public boolean checkCacheName(String cacheName) {
        return cacheName.compareTo(cache.getCacheName()) == 0;
    }

}
