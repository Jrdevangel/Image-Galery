package com.imagegallery.ImageGallery.service;

import com.imagegallery.ImageGallery.model.Image;
import com.imagegallery.ImageGallery.repository.IImageGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ImageGalleryService {
    @Autowired
    IImageGalleryRepository iImageGalleryRepository;

    public Optional<Image> getByImagesById(Integer id) {
        return iImageGalleryRepository.findById(id);
    }

    public ArrayList<Image> getAllImages() {
        return (ArrayList<Image>) iImageGalleryRepository.findAll();
    }
    public Image updateImage(int id, Image updatedImage) {
        Optional<Image> optionalTask = iImageGalleryRepository.findById(id);
        if (optionalTask.isPresent()) {
            Image existingImage = optionalTask.get();
            existingImage.setTitle(updatedImage.getTitle());
            existingImage.setDescription(updatedImage.getDescription());
            existingImage.setUrl(updatedImage.getUrl());

            return iImageGalleryRepository.save(existingImage);
        } else {
            return null;
        }
    }
    public Image addNewImage(Image image){
        return iImageGalleryRepository.save(image);
    }

    public void deleteImage(Integer id){
            iImageGalleryRepository.deleteById(id);
    }
}