package com.mao.dao;

import com.mao.model.ImageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository("imageDao")
@Mapper
public interface ImageDao {

    /**
     * 用于用户上传图片
     * */
    Integer uploadImage(ImageEntity imageEntity);



}
