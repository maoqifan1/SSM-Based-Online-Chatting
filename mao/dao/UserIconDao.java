package com.mao.dao;


import com.mao.model.UserIconEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository("userIconDao")
@Mapper
public interface UserIconDao {

    /**
     * 用于用户设置头像
     * */
    Integer insertUserIcon(UserIconEntity userIconEntity);

    /**
     * 用于获取用户的头像
     * */
    String getUserIcon(Integer userid);

    /**
     * 用于用户修改头像
     * */
    Integer updateUserIcon(UserIconEntity userIconEntity);

    /**
     * 用于查找是否该用户拥有头像
     * */
    Integer getUserId(int UserId);

}
