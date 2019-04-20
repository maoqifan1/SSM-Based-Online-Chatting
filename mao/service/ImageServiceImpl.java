package com.mao.service;

import com.mao.dao.ImageDao;
import com.mao.model.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService{
    @Autowired
    ImageDao imageDao;

    @Override
    public Boolean imageUpload(ImageEntity imageEntity) {
        if (imageDao.uploadImage(imageEntity)==1)
            return true;
        return false;
    }
}
