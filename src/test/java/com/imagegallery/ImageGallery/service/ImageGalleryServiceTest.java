package com.imagegallery.ImageGallery.service;

import com.imagegallery.ImageGallery.model.Image;
import com.imagegallery.ImageGallery.repository.IImageGalleryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
