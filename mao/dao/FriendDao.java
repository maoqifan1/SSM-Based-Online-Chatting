package com.mao.dao;


import com.mao.model.GroupEntity;
import com.mao.model.UsersGroupEntity;

import java.security.acl.Group;
import java.util.List;
import java.util.Map;

import com.mao.pojo.VUsersGroupPojo;
import com.mao.pojo.VUsersIcons;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository("friendDao")
@Mapper
public interface FriendDao {
    /**
     * 用于处理用户添加好友的各种数据库操作
     * */

    /**
     * 广义的添加好友
     * 不包括关系的具体设定
     * */
    Integer addUser(UsersGroupEntity usersGroupEntity);



    /**
     * 从数据库中查询（联结查询）
     * 当前用户所拥有的群组，及其群组下所拥有的好友
     * 形参为当前用户的ID
     * **/
    List<VUsersGroupPojo> printGroupAndFriendUnderGroup(Integer ownId);


    /**
     * 用于在删除分组前，更改分组下的好友的的分组为默认分组中
     * */
    Integer changeUsersGroupToDefault(GroupEntity groupEntity);


    /**
     * 用于用户查看好友
     * 从数据查询相关用户的数据
     * */
    VUsersIcons viewFriendById(Integer fUserId);

    /**
     * 用于确定两个用户之间的关系
     * */
    UsersGroupEntity getUserRelationShip(UsersGroupEntity usersGroupEntity);

    /**
     * 用于在另一方用户同意好友请求后，修改数据库中的对应元组
     * */
    Integer changeUserRelationship(UsersGroupEntity usersGroupEntity);
}

