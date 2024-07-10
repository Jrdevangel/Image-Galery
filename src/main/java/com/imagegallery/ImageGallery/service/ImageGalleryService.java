package com.imagegallery.ImageGallery.service;

import com.imagegallery.ImageGallery.model.Image;
import com.imagegallery.ImageGallery.repository.IImageGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Service
public class ImageGalleryService {
    @Autowired
    IImageGalleryRepository iImageGalleryRepository;


}
