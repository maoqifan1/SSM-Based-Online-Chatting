package com.mao.service;

import com.mao.dao.UserIconDao;
import com.mao.model.UserIconEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserIconServiceImpl implements UserIconService  {

    @Autowired
    private UserIconDao userIconDao;


    @Override
    public Boolean insertUserIcon(UserIconEntity userIconEntity) {
        if(userIconDao.insertUserIcon(userIconEntity) == 1)
            return true;
        return false;
    }

    @Override
    public String getUserIcon(Integer userid) {
        return userIconDao.getUserIcon(userid);
    }

    @Override
    public Boolean updateUserIcon(UserIconEntity userIconEntity) {
        if(userIconDao.updateUserIcon(userIconEntity)==1)
            return true;
        return false;
    }

    @Override
    public Boolean whetherHasIcon(int userId) {
        return userIconDao.getUserId(userId) == 0?false:true;
    }
}
