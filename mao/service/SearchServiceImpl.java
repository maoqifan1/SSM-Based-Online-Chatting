package com.mao.service;

import com.mao.dao.SearchDao;
import com.mao.pojo.VUsersIcons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public List<VUsersIcons> searchUserByEmail(String userEmail) {
        return searchDao.searchUserByEmail(userEmail);
    }

    @Override
    public List<VUsersIcons> searchUserByID(Integer userId) {
        return searchDao.searchUserByID(userId);
    }

    @Override
    public List<VUsersIcons> searchUserByPhone(String phoneNumber) {
        return searchDao.searchUserByPhone(phoneNumber);
    }

    @Override
    public List<VUsersIcons> searchUserByUserName(String userName) {
        return searchDao.searchUserByUserName(userName);
    }
}
