package com.imagegallery.ImageGallery.service;

import com.imagegallery.ImageGallery.model.Image;
import com.imagegallery.ImageGallery.repository.IImageGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImageGalleryService {
    @Autowired
    IImageGalleryRepository iImageGalleryRepository;

    public Image addNewImage(Image image){
        return iImageGalleryRepository.save(image);
    }
}
