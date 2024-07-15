package com.imagegallery.ImageGallery.controller;

import com.imagegallery.ImageGallery.services.ImageGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ImageGalleryController {
    @Autowired
    ImageGalleryService imageGalleryService;

    @DeleteMapping(path = "/images/{id}")
    public void deleteImage(@PathVariable Integer id){
        imageGalleryService.deleteImage(id);
    }
}
