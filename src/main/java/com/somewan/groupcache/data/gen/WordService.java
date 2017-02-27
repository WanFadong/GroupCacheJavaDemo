package com.somewan.groupcache.data.gen;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.somewan.groupcache.data.dao.WordDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wan on 2017/2/27.
 */
@Service
public class WordService {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private WordDao wordDao;
    int count;

    public void recordWord(char word, int count1, int count2, int count3) {
        int count = count1 + count2 + count3;
        try {
            wordDao.insertWord(word, count1, count2, count3, count);
        } catch (MySQLIntegrityConstraintViolationException me) {
            count++;
            logger.error("写入数据失败。", me);
        }
    }
}
