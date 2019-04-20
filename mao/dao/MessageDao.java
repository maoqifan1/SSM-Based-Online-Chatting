package com.mao.dao;

import com.mao.model.UserMessageEntity;
import com.mao.pojo.VUserUnreadMessageCounts;
import com.mao.pojo.VUsersMessagesPojo;

import java.util.List;
import java.util.Map;

public interface MessageDao {
    /**
     * 此接口用于定义操作数据库中关于用户聊天的数据
     * */


    /**
     * @param userMessageEntity 用于存放用户聊天信息的实体类对象
     * @return 返回0表示插入失败，返回1表示插入成功
     * */
    Integer insertMessage(UserMessageEntity userMessageEntity);


    /**
     * @param userMap 用于存放发送信息的用户id 和 接收信息的用户id
     * @return 返回一个泛型为messagePojo的list
     * */
    List<VUsersMessagesPojo> getMessage(Map<String,Integer> userMap);

    /**
     * 用于查找当前用户有多少条未读消息的数量
     * @param cUserId 是当前用户的id(current User ID)
     * @return 返回的是未读消息的数量
     * */

    Integer getUnreadMessageAmounts(int cUserId);

    /**
     * 用户获取当前用户指定用户的聊天的未读消息的数量
     * @param
     *        messageFuserId 发送消息的用户的id
     *
    * */
    List<VUserUnreadMessageCounts> getSpecifiedUnreadMessageAmounts(Integer messageFuserId);

    /**
     * 用于在用户阅读消息后，修改消息的状态
     * @param userMap 用于存放发送信息的用户id 和 接收信息的用户id
     * @return 返回一个整数，为0表示更新失败，为1表示更新成功
     * */
    Integer updateMessageStatusToRead(Map<String,Integer> userMap);
}
