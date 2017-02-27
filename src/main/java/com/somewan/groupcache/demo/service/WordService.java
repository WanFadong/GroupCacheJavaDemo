package com.somewan.groupcache.demo.service;

import com.alibaba.fastjson.JSON;
import com.somewan.cache.getter.LocalGetter;
import com.somewan.cache.result.Result;
import com.somewan.cache.result.ResultCode;
import com.somewan.groupcache.demo.dao.WordDao;
import com.somewan.groupcache.demo.model.Word;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

/**
 * Created by wan on 2017/2/27.
 */
public class WordService implements LocalGetter {
    private static final Logger logger = LogManager.getLogger();
    private WordDao wordDao;

    public WordService() {
        try {
            String resource = "mybatis_config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();
            wordDao = session.getMapper(WordDao.class);
            logger.info("初始化 wordService 成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Result get(String nameSpace, String key) {
        logger.debug("namespace: {}, key: {}", nameSpace, key);
        System.out.println("key" + key);
        Word word = wordDao.selectWord(key);
        logger.debug("word = {}", JSON.toJSONString(word));
        System.out.println("result" + JSON.toJSONString(word));
        if (word == null) {
            return Result.errorResult(ResultCode.NOT_FOUND);
        }
        switch (nameSpace) {
            case "one":
                return Result.successResult(word.getCount1());
            case "two":
                return Result.successResult(word.getCount2());
            case "three":
                return Result.successResult(word.getCount3());
            default:
                return Result.errorResult(ResultCode.BAD_REQUEST);// 不存在的命名空间。
        }
    }
}
