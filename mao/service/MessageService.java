package com.mao.service;

import com.mao.model.UserMessageEntity;
import com.mao.pojo.VUserUnreadMessageCounts;
import com.mao.pojo.VUsersMessagesPojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("messageService")
public interface MessageService {

    /**
     * @param userMessageEntity 用于存放用户聊天信息的实体类对象
     * @return 返回false表示插入失败，返回true表示插入成功
     * */
    Boolean insertMessage(UserMessageEntity userMessageEntity);


    /**
     * @param userMap 用于存放发送信息的用户id 和 接收信息的用户id
     * @return 返回一个泛型为messagePojo的list
     * */
    List<VUsersMessagesPojo> getMessage(Map<String,Integer> userMap);

    /**
     * 用于确定当前用户之间有多少条未读消息的数量
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
     * @return 返回一个布尔类型，为false表示更新失败，为true表示更新成功
     * */
    Boolean updateMessageStatusToRead(Map<String,Integer> userMap);
}
