package com.imagegallery.ImageGallery.controller;

import com.imagegallery.ImageGallery.model.Image;
import com.imagegallery.ImageGallery.service.ImageGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ImageGalleryController {
    @Autowired
    private ImageGalleryService imageGalleryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Image> getAllImages() {
        return imageGalleryService.getAllImages();
    }

    @PutMapping(path = "/images/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Image> updateImage(@PathVariable("id") int id, @RequestBody Image updatedImage) {
        Image updated = imageGalleryService.updateImage(id, updatedImage);
        return ResponseEntity.ok(updated);
    }

}
