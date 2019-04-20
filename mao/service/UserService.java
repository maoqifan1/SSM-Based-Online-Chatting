package com.mao.service;

import com.mao.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userService")
public interface UserService {
    UserEntity userLogin(Map<String, Object> userMapper);

    Integer userRegister(UserEntity userEntity);

    Boolean existEmail(String email);

    Boolean existPhone(String phone);

    Boolean existId(int id);
}
