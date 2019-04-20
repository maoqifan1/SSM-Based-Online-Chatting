package com.mao.service;

import com.mao.model.ImageEntity;
import org.springframework.stereotype.Service;

@Service("imageService")
public interface ImageService {
    Boolean imageUpload(ImageEntity imageEntity);


}
