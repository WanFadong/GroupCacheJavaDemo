package com.somewan.groupcache.demo.service;

import com.alibaba.fastjson.JSON;
import com.somewan.cache.GroupCache;
import com.somewan.cache.getter.LocalGetter;
import com.somewan.cache.result.Result;
import com.somewan.cache.result.ResultCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wan on 2017/2/3.
 */
public class GroupCacheService {
    private static final Logger logger = LogManager.getLogger();
    GroupCache cache;

    /**
     * 初始化GroupCache
     */
    public void init() {
        String cacheName = "GroupCache";
        String[] peers = {"localhost:20000", "localhost:30000"};

        LocalGetter getter = new WordService();
        cache = new GroupCache(cacheName, getter);
        cache.initPeers(peers[1], peers);// 不同机器使用不同节点。
        cache.newNamespace("one");
        cache.newNamespace("two");
        cache.newNamespace("three");
        logger.info("初始化 GroupCacheService 成功");
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
