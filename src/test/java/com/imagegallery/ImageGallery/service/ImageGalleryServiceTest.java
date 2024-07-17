package com.imagegallery.ImageGallery.service;

import com.imagegallery.ImageGallery.model.Image;
import com.imagegallery.ImageGallery.repository.IImageGalleryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageGalleryServiceTest {

    @Mock
    private IImageGalleryRepository iImageGalleryRepository;

    @InjectMocks
    private ImageGalleryService imageGalleryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewImage() {
        Image image = new Image();
        image.setId(1);
        image.setTitle("Test Image");
        image.setDescription("Test Description");
        image.setUrl("http://test.com/image.jpg");

        when(iImageGalleryRepository.save(any(Image.class))).thenReturn(image);

        Image result = imageGalleryService.addNewImage(image);

        assertEquals(image.getId(), result.getId());
        assertEquals(image.getTitle(), result.getTitle());
        assertEquals(image.getDescription(), result.getDescription());
        assertEquals(image.getUrl(), result.getUrl());
    }

    @Test
    public void testDeleteImage() {
        Integer imageId = 1;

        imageGalleryService.deleteImage(imageId);

        verify(iImageGalleryRepository).deleteById(imageId);
    }

    @Test
    void testGetAllImages() {
        ArrayList<Image> images = new ArrayList<>();
        Image image1 = new Image();
        image1.setId(1);
        image1.setTitle ("Test Title 1");
        image1.setDescription("Test Description 1");
        image1.setUrl("http://prueba.com/image1.jpg");

        Image image2 = new Image();
        image2.setTitle ("Test Title 2");
        image2.setId(2);
        image2.setDescription("Test Description 2");
        image2.setUrl("http://prueba.com/image2.jpg");

        images.add(image1);
        images.add(image2);

        when(iImageGalleryRepository.findAll()).thenReturn(images);

        ArrayList<Image> result = imageGalleryService.getAllImages();

        assertEquals(2, result.size());
        assertEquals(image1, result.get(0));
        assertEquals(image2, result.get(1));
    }

    @Test
    void testUpdateImageWhenImageExists() {
        Image existingImage = new Image();
        existingImage.setId(1);
        existingImage.setTitle("Old Title");
        existingImage.setDescription("Old Description");
        existingImage.setUrl("http://example.com/old-image.jpg");

        Image updatedImage = new Image();
        updatedImage.setTitle("New Title");
        updatedImage.setDescription("New Description");
        updatedImage.setUrl("http://example.com/new-image.jpg");

        when(iImageGalleryRepository.findById(1)).thenReturn(Optional.of(existingImage));
        when(iImageGalleryRepository.save(any(Image.class))).thenReturn(existingImage);

        Image result = imageGalleryService.updateImage(1, updatedImage);

        assertEquals("New Description", result.getDescription());
        assertEquals("http://example.com/new-image.jpg", result.getUrl());
    }

    @Test
    void testUpdateImageWhenImageDoesNotExist() {

        when(iImageGalleryRepository.findById(1)).thenReturn(Optional.empty());

        Image result = imageGalleryService.updateImage(1, new Image());

        assertNull(result);
    }
}
