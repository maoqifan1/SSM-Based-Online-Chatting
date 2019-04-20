package com.mao.service;

import com.mao.model.GroupEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("groupService")
public interface GroupService {
    Boolean addGroup(GroupEntity groupEntity);

    Boolean delGroup(GroupEntity groupEntity);

    Boolean updGroup(GroupEntity groupEntity);

    List<String> getAllGroups(Integer cUserId);

    Boolean whetherHasThisGroup(GroupEntity groupEntity);
}
