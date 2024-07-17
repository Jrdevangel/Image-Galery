package com.imagegallery.ImageGallery.services;

import com.imagegallery.ImageGallery.repository.IImageGalleryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

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
    public void testDeleteImage() {
        Integer imageId = 1;

        imageGalleryService.deleteImage(imageId);

        verify(iImageGalleryRepository).deleteById(imageId);
    }
}
