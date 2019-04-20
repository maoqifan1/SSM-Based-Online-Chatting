package com.mao.service;

import com.mao.model.UserIconEntity;
import org.springframework.stereotype.Service;

@Service("userIconService")
public interface UserIconService {

    Boolean insertUserIcon(UserIconEntity userIconEntity);

    String getUserIcon(Integer userid);

    Boolean updateUserIcon(UserIconEntity userIconEntity);

    Boolean whetherHasIcon(int userId);
}
