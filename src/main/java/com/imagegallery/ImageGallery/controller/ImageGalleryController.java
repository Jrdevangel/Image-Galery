package com.imagegallery.ImageGallery.controller;

import com.imagegallery.ImageGallery.service.ImageGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ImageGalleryController {
    @Autowired
    ImageGalleryService imageGalleryService;


}
