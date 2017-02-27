package com.somewan.groupcache.data.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wan on 2017/2/27.
 */
@Repository
public interface WordDao {
    void insertWord(@Param("word") char word, @Param("count1") int count1,
                    @Param("count2") int count2, @Param("count3") int count3,
                    @Param("count") int count) throws MySQLIntegrityConstraintViolationException;
}
