package com.mao.dao;

import com.mao.pojo.VUsersIcons;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 这个数据访问层接口用于处理搜索内容
 * 并访问对应的表及其字段
 * */
@Repository("searchDao")
@Mapper
public interface SearchDao {
    /**
     * 根据邮箱地址搜索用户，形参为邮箱地址；支持模糊查询或者正则表达式查询
     * 返回一个包含用户对象的集合(若没有则返回null)
     * */
    List<VUsersIcons> searchUserByEmail(String userEmail);

    /**
     * 根据用户ID搜索用户，形参为用户ID；支持模糊查询或者正则表达式查询
     * 返回一个包含用户对象的集合(若没有则返回null)
     * */
    List<VUsersIcons> searchUserByID(Integer userId);

    /**
     * 根据用户电话搜索用户，形参为电话号码；支持模糊查询或者正则表达式查询
     * 返回一个包含用户对象的集合(若没有则返回null)
     * */
    List<VUsersIcons> searchUserByPhone(String phoneNumber);


    /**
     * 根据用户名搜索用户，形参为用户名；支持模糊查询或者正则表达式查询
     * 返回一个包含用户对象的集合(若没有则返回null)
     * */
    List<VUsersIcons> searchUserByUserName(String userName);


}
