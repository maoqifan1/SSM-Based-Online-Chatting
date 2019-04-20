package com.mao.service;

import com.mao.model.UserInformEntity;
import com.mao.pojo.userInformPojo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("infoService")
public interface InfoService {
    Boolean sendInfo(UserInformEntity userInformEntity);

    List<userInformPojo> showInfo(Integer ownerUserId);

    Boolean updateActive(UserInformEntity userInformEntity);
}
