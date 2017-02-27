package com.somewan.groupcache.demo.dao;

import com.somewan.groupcache.demo.model.Word;

/**
 * Created by wan on 2017/2/27.
 */
public interface WordDao {
    Word selectWord(String word);
}
