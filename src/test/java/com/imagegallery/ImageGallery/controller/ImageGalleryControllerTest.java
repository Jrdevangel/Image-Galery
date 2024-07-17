package com.imagegallery.ImageGallery.controller;

import com.imagegallery.ImageGallery.model.Image;
import com.imagegallery.ImageGallery.service.ImageGalleryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ImageGalleryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ImageGalleryService imageGalleryService;

    @InjectMocks
    private ImageGalleryController imageGalleryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(imageGalleryController).build();
    }

    @Test
    public void testAddNewImage() throws Exception {
        Image image = new Image();
        image.setId(1);
        image.setTitle("Sample Image");
        image.setDescription("Sample Description");
        image.setUrl("http://example.com/image.jpg");

        when(imageGalleryService.addNewImage(any(Image.class))).thenReturn(image);

        ObjectMapper objectMapper = new ObjectMapper();
        String imageJson = objectMapper.writeValueAsString(image);

        mockMvc.perform(post("/api/v1/images")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(imageJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Sample Image"))
                .andExpect(jsonPath("$.description").value("Sample Description"))
                .andExpect(jsonPath("$.url").value("http://example.com/image.jpg"));
    }

    @Test
    public void testDeleteImage() throws Exception {
        Integer imageId = 1;

        doNothing().when(imageGalleryService).deleteImage(imageId);

        mockMvc.perform(delete("/api/v1/images/{id}", imageId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(imageGalleryService).deleteImage(imageId);
    }

    @Test
    public void testGetAllImages() throws Exception {
        Image image = new Image();
        image.setId(1);
        image.setTitle("Test Image");
        image.setDescription("Test Description");
        image.setUrl("http://example.com/image.jpg");


        when(imageGalleryService.getAllImages()).thenReturn(new ArrayList<>(List.of(image)));

        mockMvc.perform(get("/api/v1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Image"))
                .andExpect(jsonPath("$[0].description").value("Test Description"))
                .andExpect(jsonPath("$[0].url").value("http://example.com/image.jpg"));
    }

    @Test
    public void testUpdateImage() throws Exception {
        Image existingImage = new Image();
        existingImage.setId(1);
        existingImage.setTitle("Old Title");
        existingImage.setDescription("Old Description");
        existingImage.setUrl("http://example.com/old_image.jpg");

        Image updatedImage = new Image();
        updatedImage.setTitle("Updated Title");
        updatedImage.setDescription("Updated Description");
        updatedImage.setUrl("http://example.com/new_image.jpg");


        when(imageGalleryService.updateImage(anyInt(), any(Image.class))).thenReturn(updatedImage);


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(updatedImage);


        mockMvc.perform(put("/api/v1/images/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.url").value("http://example.com/new_image.jpg"));
    }
}