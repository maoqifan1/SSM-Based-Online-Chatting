package com.mao.service;

import com.mao.dao.InfoDao;
import com.mao.model.UserInformEntity;
import com.mao.pojo.userInformPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoDao infoDao;

    @Override
    public Boolean sendInfo(UserInformEntity userInformEntity) {
        if(infoDao.sendInfo(userInformEntity) == 1)
            return true;
        return false;
    }

    @Override
    public List<userInformPojo> showInfo(Integer ownerUserId) {
        return infoDao.showInfo(ownerUserId);
    }

    @Override
    public Boolean updateActive(UserInformEntity userInformEntity) {
        if (infoDao.updateActive(userInformEntity) == 1)
            return true;
        else
            return false;
    }
}
