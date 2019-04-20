package com.mao.dao;

import com.mao.model.UserConversionEntity;
import com.mao.pojo.VUsersIcons;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String args[]) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
       MessageDao messageDao = (MessageDao) applicationContext.getBean("messageDao");

    }
}
