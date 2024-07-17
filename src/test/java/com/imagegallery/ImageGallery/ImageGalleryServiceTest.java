package com.imagegallery.ImageGallery;
import com.imagegallery.ImageGallery.model.Image;
import com.imagegallery.ImageGallery.repository.IImageGalleryRepository;
import com.imagegallery.ImageGallery.service.ImageGalleryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;

class ImageGalleryServiceTest {

    @Mock
    private IImageGalleryRepository iImageGalleryRepository;

    @InjectMocks
    private ImageGalleryService imageGalleryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllImages() {
        // Preparar los datos de prueba
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

        // Configurar el comportamiento simulado del repositorio
        when(iImageGalleryRepository.findAll()).thenReturn(images);

        // Llamar al método bajo prueba
        ArrayList<Image> result = imageGalleryService.getAllImages();

        // Verificar el resultado
        assertEquals(2, result.size());
        assertEquals(image1, result.get(0));
        assertEquals(image2, result.get(1));
    }

    @Test
    void testUpdateImageWhenImageExists() {
        // Preparar los datos de prueba
        Image existingImage = new Image();
        existingImage.setId(1);
        existingImage.setTitle("Old Title");
        existingImage.setDescription("Old Description");
        existingImage.setUrl("http://example.com/old-image.jpg");

        Image updatedImage = new Image();
        updatedImage.setTitle("New Title");
        updatedImage.setDescription("New Description");
        updatedImage.setUrl("http://example.com/new-image.jpg");

        // Configurar el comportamiento simulado del repositorio
        when(iImageGalleryRepository.findById(1)).thenReturn(Optional.of(existingImage));
        when(iImageGalleryRepository.save(any(Image.class))).thenReturn(existingImage);

        // Llamar al método bajo prueba
        Image result = imageGalleryService.updateImage(1, updatedImage);

        // Verificar el resultado
        assertEquals("New Description", result.getDescription());
        assertEquals("http://example.com/new-image.jpg", result.getUrl());
    }

    @Test
    void testUpdateImageWhenImageDoesNotExist() {
        // Configurar el comportamiento simulado del repositorio
        when(iImageGalleryRepository.findById(1)).thenReturn(Optional.empty());

        // Llamar al método bajo prueba
        Image result = imageGalleryService.updateImage(1, new Image());

        // Verificar el resultado
        assertNull(result);
    }
}