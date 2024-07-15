package com.imagegallery.ImageGallery.services;

import com.imagegallery.ImageGallery.repository.IImageGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageGalleryService {
    @Autowired
    IImageGalleryRepository iImageGalleryRepository;

    public void deleteImage(Integer id){
            iImageGalleryRepository.deleteById(id);
    }
}