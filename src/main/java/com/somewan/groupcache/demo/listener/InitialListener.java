package com.somewan.groupcache.demo.listener;

import com.somewan.groupcache.demo.service.GroupCacheService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by wan on 2017/2/3.
 */
public class InitialListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        // 初始化GroupCache集群。
        GroupCacheService service = GroupCacheService.getInstance();
        service.init();
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
