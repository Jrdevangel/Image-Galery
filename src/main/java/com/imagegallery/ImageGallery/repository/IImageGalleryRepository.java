package com.imagegallery.ImageGallery.repository;

import com.imagegallery.ImageGallery.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface IImageGalleryRepository extends CrudRepository<Image, Integer> {
}
