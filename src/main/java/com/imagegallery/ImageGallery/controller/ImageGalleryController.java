package com.imagegallery.ImageGallery.controller;

import com.imagegallery.ImageGallery.model.Image;
import com.imagegallery.ImageGallery.service.ImageGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ImageGalleryController {
    @Autowired
    private ImageGalleryService imageGalleryService;

    @GetMapping(path = "/images")
    public ArrayList<Image> getAllImages() {
        return imageGalleryService.getAllImages();
    }
    @PutMapping(path = "/images/{id}")
    public Image updateImage(@PathVariable("id") int id, @RequestBody Image updatedImage) {
        return imageGalleryService.updateImage(id, updatedImage);
    }

}
