package com.somewan.groupcache.data;

import com.somewan.groupcache.data.dao.WordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试 mybatis + spring 框架
 * Created by wan on 2017/2/27.
 */
@Service
public class TestService {
    @Autowired
    private WordDao wordDao;

    public void insert() {
        //wordDao.insertWord('t', 1, 1, 1, 3);
    }
}
