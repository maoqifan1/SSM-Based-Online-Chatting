package com.mao.dao;

import com.mao.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 这个数据访问层接口用于访问数据库中一切关于用户的数据
 * */
@Repository("userDao")
@Mapper
public interface UserDao {

    /**
     * userLogin方法用于处理用户登录，返回一个用户对象，若没有这个用户则返回null值
     * 形参是一个map对象存储用户输入的用户id 和密码
     * */
    UserEntity userLogin(Map<String, Object> userMapper);

    /**
     * userRegister方法用于处理用户注册，返回一个整数为1时表示成功；为0时表示失败
     * 形参是一个用户对象实体，存储用户输入的关于用户的信息
     * */
    Integer userRegister(UserEntity userEntity);

    /**
     * selectEmail方法用于选择指定邮箱，用于判断数据库中是否有与用户输入相同的邮箱地址，形参为邮箱地址字符串
     * 返回一个字符串如果为null则表示数据库中没有相同的邮箱地址
     * */
    String selectEmail(String email);

    /**
     * selectPhone方法用于选择指定电话，用于判断数据库中是否有与用户输入相同的电话号码，形参为电话号码字符串
     * 返回一个字符串如果为null则表示数据库中没有相同的电话号码
     * */
    String selectPhone(String phone);

    /**
     * selectId方法选择指定用户id，用于判断数据库中是否有与当前自动生成相同的用户id，形参为整数类型的用户id
     * 返回一个字符串如果为0则表示数据库中没有相同的用户id
     * */
    Integer selectId(int id);

}
