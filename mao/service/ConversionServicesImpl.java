package com.mao.service;

import com.mao.dao.ConversionsDao;
import com.mao.pojo.VUsersConversionsPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ConversionServicesImpl implements ConversionServices {
    @Autowired
    ConversionsDao conversionsDao;

    @Override
    public Boolean insertConversionData(Map<String,Integer> conversionMap) {
        if (conversionsDao.insertConversionData(conversionMap) == 1)
            return true;
        return false;
    }

    @Override
    public List<VUsersConversionsPojo> getConversionDataById(int ownId) {
        return conversionsDao.getConversionDataById(ownId);
    }
}
