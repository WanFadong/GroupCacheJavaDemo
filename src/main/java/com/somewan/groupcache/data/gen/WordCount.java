package com.somewan.groupcache.data.gen;

import com.somewan.groupcache.data.model.Word;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 对三体进行 WordCount(CharCount)
 * Created by wan on 2017/2/27.
 */
public class WordCount {
    private static final Logger logger = LogManager.getLogger();
    private FileService fileService;
    private WordService wordService;

    public static void main(String[] args) {
        String dir = "/Users/wan/workspace/data/";
        String[] paths = {dir + "1.txt", dir + "2.txt",dir + "3.txt"};
        //String[] paths = {dir + "0.txt"};
        WordCount count = new WordCount();
        count.wordCount(paths);
        System.out.println("count over");
    }

    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        fileService = context.getBean("fileService", FileService.class);
        wordService = context.getBean("wordService", WordService.class);
    }

    /**
     * WordCount 并写入 DB
     * @param filePaths
     */
    public void wordCount(String[] filePaths) {
        init();
        Map<Character, Word> map = new HashMap<Character, Word>(10000);
        try {
            // 统计
            for (int i = 0; i < filePaths.length; i++) {
                countOneBook(filePaths[i], i + 1, map);
            }
            logger.info("共有字{}个", map.size());

            //写入数据库
            for(Character c: map.keySet()) {
                Word w = map.get(c);
                wordService.recordWord(c, w.getCount1(), w.getCount2(), w.getCount3());
            }
            logger.info("共重复{}次", wordService.count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void countOneBook(String filePath, int flag, Map<Character, Word> map) throws IOException {
        fileService.initFileReader(filePath);
        String line;
        while ((line = fileService.readLine()) != null) {
            // 分字
            int len = line.length();
            char c;
            for (int i = 0; i < len; i++) {
                c = line.charAt(i);
                increase(c, flag, map);
            }
        }
    }

    private void increase(char c, int flag, Map<Character, Word> map) {
        if (map.containsKey(c)) {
            Word w = map.get(c);
            switch (flag) {
                case 1:
                    w.setCount1(w.getCount1() + 1);
                    break;
                case 2:
                    w.setCount2(w.getCount2() + 1);
                    break;
                case 3:
                    w.setCount3(w.getCount3() + 1);
                    break;
                default:
                    // 不可能
                    return;
            }
        } else {
            Word w = new Word();
            w.setWord(c);
            switch (flag) {
                case 1:
                    w.setCount1(1);
                    break;
                case 2:
                    w.setCount2(1);
                    break;
                case 3:
                    w.setCount3(1);
                    break;
                default:
                    // 不可能
                    return;
            }
            map.put(c, w);
        }
    }
}
