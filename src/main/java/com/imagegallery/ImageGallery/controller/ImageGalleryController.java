package com.imagegallery.ImageGallery.controller;

import com.imagegallery.ImageGallery.model.Image;
import com.imagegallery.ImageGallery.service.ImageGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ImageGalleryController {
    @Autowired
    ImageGalleryService imageGalleryService;

    @PostMapping (path = "/images")
    public Image addNewImage(@RequestBody Image image){
        return imageGalleryService.addNewImage(image);
    }

    @DeleteMapping(path = "/images/{id}")
    public void deleteImage(@PathVariable Integer id){
        imageGalleryService.deleteImage(id);
    }
}
