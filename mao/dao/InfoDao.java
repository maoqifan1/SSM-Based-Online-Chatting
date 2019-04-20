package com.mao.dao;

import com.mao.model.UserInformEntity;
import com.mao.pojo.userInformPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 这个接口用于定义关于从数据库中取出关于用户通知的对象
 * */
@Repository("infoDao")
@Mapper
public interface InfoDao {


    /**
     * 此方法用于将好友请求信息插入数据库
     * 参数为 发送信息的用户id,接受信息的用户id,信息的内容
     * */
    Integer sendInfo(UserInformEntity userInformEntity);


    /**
     * 此方法用于从数据库中检索与当前用户相关的通知
     * 参数为接收信息的用户id
     *
     * */
    List<userInformPojo> showInfo(Integer ownUserId);


    /**
     * 用于消息在被阅读且执行后，更改消息的active状态为yes
     *  */
    Integer updateActive(UserInformEntity userInformEntity);
}
