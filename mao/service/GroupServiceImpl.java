package com.mao.service;

import com.mao.dao.GroupDao;
import com.mao.model.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupDao groupDao;

    @Override
    public Boolean addGroup(GroupEntity groupEntity) {
        if (groupDao.addGroup(groupEntity) == 1)
            return true;
        else
            return false;
    }

    @Override
    public Boolean delGroup(GroupEntity groupEntity) {
        if (groupDao.delGroup(groupEntity) == 1)
            return true;
        else
            return false;
    }

    @Override
    public Boolean updGroup(GroupEntity groupEntity) {
        if (groupDao.updGroup(groupEntity) == 1)
            return true;
        else
            return false;
    }

    @Override
    public List<String> getAllGroups(Integer cUserId) {
        return groupDao.getAllGroups(cUserId);
    }

    @Override
    public Boolean whetherHasThisGroup(GroupEntity groupEntity) {
        if(groupDao.getOwnerGroup(groupEntity) == null){
            return false;
        }
        else if (groupDao.getOwnerGroup(groupEntity).equals(groupEntity.getGroupname()))
            return true;
        else
            return false;
    }
}
