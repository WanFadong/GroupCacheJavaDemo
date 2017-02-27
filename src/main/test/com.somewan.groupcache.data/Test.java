package com.somewan.groupcache.data;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 mybatis + spring 框架
 * Created by wan on 2017/2/27.
 */
public class Test {

//    public static void main(String[] args) {
//        Test test = new Test();
//        try {
//            test.insert();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void insert() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        TestService service = context.getBean("testService", TestService.class);
        service.insert();
    }
}
