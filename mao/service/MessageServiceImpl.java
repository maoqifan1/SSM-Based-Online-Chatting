package com.mao.service;

import com.mao.dao.MessageDao;
import com.mao.model.UserMessageEntity;
import com.mao.pojo.VUserUnreadMessageCounts;
import com.mao.pojo.VUsersMessagesPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;

    @Override
    public Boolean insertMessage(UserMessageEntity userMessageEntity) {
        if (messageDao.insertMessage(userMessageEntity) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<VUsersMessagesPojo> getMessage(Map<String, Integer> userMap) {
        return messageDao.getMessage(userMap);
    }

    @Override
    public Integer getUnreadMessageAmounts(int cUserId) {
        return messageDao.getUnreadMessageAmounts(cUserId);
    }

    @Override
    public List<VUserUnreadMessageCounts> getSpecifiedUnreadMessageAmounts(Integer messageFuserId) {
        return messageDao.getSpecifiedUnreadMessageAmounts(messageFuserId);
    }

    @Override
    public Boolean updateMessageStatusToRead(Map<String, Integer> userMap) {
        if (messageDao.updateMessageStatusToRead(userMap) == 1)
            return true;
        return false;
    }
}
