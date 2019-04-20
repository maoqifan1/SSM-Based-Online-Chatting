package com.mao.service;

import com.mao.model.UserConversionEntity;
import com.mao.pojo.VUsersConversionsPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("conversionServices")
public interface ConversionServices {
    /**
     * @param conversionMap 是包含当前会话对象的用户id和对话对象的的用户id的集合
     * @return 返回一个布尔型 false表示插入失败，true表示插入成功
     * */
    Boolean insertConversionData(Map<String,Integer> conversionMap);


    /**
     * @param ownId 是当前用户的id
     * @return 返回一个包含用户会话对象的列表
     * */
    List<VUsersConversionsPojo> getConversionDataById(int ownId);
}
