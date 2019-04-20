package com.mao.service;

import com.mao.dao.FriendDao;
import com.mao.model.GroupEntity;
import com.mao.model.UsersGroupEntity;
import com.mao.pojo.VUsersGroupPojo;
import com.mao.pojo.VUsersIcons;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.acl.Group;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendDao friendDao;

    @Override
    public Boolean AddUser(UsersGroupEntity usersGroupEntity) {
        int result = friendDao.addUser(usersGroupEntity);
        if (result == 1)
            return true;
        return false;
    }

    @Override
    public List<VUsersGroupPojo> printGroupAndFriendUnderGroup(Integer ownId) {
        return friendDao.printGroupAndFriendUnderGroup(ownId);
    }

    @Override
    public Boolean changeUsersGroupToDefault(GroupEntity groupEntity) {
        if (friendDao.changeUsersGroupToDefault(groupEntity) == 1)
            return true;
        return false;
    }

    @Override
    public VUsersIcons viewFriendById(Integer fUserId) {
        return friendDao.viewFriendById(fUserId);
    }

    @Override
    public Boolean changeUserRelationShip(UsersGroupEntity usersGroupEntity) {
        Integer result = friendDao.changeUserRelationship(usersGroupEntity);
        if (result == 1)
            return true;
        return false;
    }
}
