package com.mao.test;

import com.mao.dao.UserDao;
import com.mao.model.UserEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String args[]){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao)applicationContext.getBean("userDao");
        Map<String, Object> userMaper = new HashMap<>();
        userMaper.put("userid", 27436981);
        userMaper.put("password","qq214031mm@");
        UserEntity userEntity = userDao.userLogin(userMaper);
        System.out.println(userEntity.getUsername());
    }
}
