package com.mao.service;

import com.mao.dao.UserDao;
import com.mao.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity userLogin(Map<String, Object> userMap){
        return userDao.userLogin(userMap);
    }

    @Override
    public Integer userRegister(UserEntity userEntity){
        return userDao.userRegister(userEntity);
    }


    @Override
    public Boolean existEmail(String email) {
        return email.equals(userDao.selectEmail(email));
    }

    @Override
    public Boolean existPhone(String phone) {
        return phone.equals(userDao.selectPhone(phone));
    }

    @Override
    public Boolean existId(int id) {
        return userDao.selectId(id) == null?false:true;
    }


}
