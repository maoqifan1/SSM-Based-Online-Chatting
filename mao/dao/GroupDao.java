package com.mao.dao;

import com.mao.model.GroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 这个接口用于管理用户分组
 * */
@Repository("groupDao")
@Mapper
public interface GroupDao {


    /**
     * 用于用户添加分组
     * 形参为分组实体对象
     * */
    Integer addGroup(GroupEntity groupEntity);

    /**
     * 返回指定的当前用户分组
     * 用于判断是否已有此分组
     * 防止重复添加分组
     * */
    String getOwnerGroup(GroupEntity groupEntity);

    /**
     * 用于用户删除分组
     * 形参为分组实体对象
     * */
    Integer delGroup(GroupEntity groupEntity);



    /**
     * 用于用户更新分组
     * 形参为分组实体对象
     * */
    Integer updGroup(GroupEntity groupEntity);

    /**
     * 用于查询当前用户的所有分组
     * 形参为当前用户id
     * 返回一个泛型为String的set(为了避免重复的分组)
     * */
    List<String> getAllGroups(int curUserId);

}
