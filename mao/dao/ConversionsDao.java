package com.mao.dao;

import com.mao.pojo.VUsersConversionsPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("conversionsDao")
@Mapper
public interface ConversionsDao {
    /*
     * 该接口用于对会话数据进行操作
     * */

    /**
     * @param conversionMap 是包含当前会话对象的用户id和对话对象的的用户id的集合
     * @return 返回一个整型 0表示插入失败，1表示插入成功
     * */
    Integer insertConversionData(Map<String,Integer> conversionMap);


    /**
     * @param ownId 是当前用户的id
     * @return 返回一个包含用户会话对象的列表
     * */
    List<VUsersConversionsPojo> getConversionDataById(int ownId);

}
