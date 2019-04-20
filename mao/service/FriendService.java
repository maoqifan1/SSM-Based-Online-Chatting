package com.mao.service;

import com.mao.model.GroupEntity;
import com.mao.model.UsersGroupEntity;
import com.mao.pojo.VUsersGroupPojo;
import com.mao.pojo.VUsersIcons;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("friendService")
public interface FriendService {
    Boolean AddUser(UsersGroupEntity usersGroupEntity);

    List<VUsersGroupPojo> printGroupAndFriendUnderGroup(Integer ownId);

    VUsersIcons viewFriendById(Integer fUserId);

    /**
     * 用于在删除分组前，更改分组下的好友的的分组为默认分组中
     * */
    Boolean changeUsersGroupToDefault(GroupEntity groupEntity);


    Boolean changeUserRelationShip(UsersGroupEntity usersGroupEntity);
}
