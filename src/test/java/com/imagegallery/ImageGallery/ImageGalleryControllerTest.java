package com.imagegallery.ImageGallery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imagegallery.ImageGallery.model.Image;
import com.imagegallery.ImageGallery.service.ImageGalleryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImageGalleryController.class)
public class ImageGalleryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageGalleryService imageGalleryService;

    @Test
    public void testGetAllImages() throws Exception {
        // Prepare test data
        Image image = new Image();
        image.setId(1);
        image.setTitle("Test Image");
        image.setDescription("Test Description");
        image.setUrl("http://example.com/image.jpg");

        // When the service is called, return a list with one image
        when(imageGalleryService.getAllImages()).thenReturn(new ArrayList<>(List.of(image)));

        // Perform the GET request and check the response
        mockMvc.perform(get("/api/v1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Image"))
                .andExpect(jsonPath("$[0].description").value("Test Description"))
                .andExpect(jsonPath("$[0].url").value("http://example.com/image.jpg"));
    }

    @Test
    public void testUpdateImage() throws Exception {
        // Prepare test data
        Image existingImage = new Image();
        existingImage.setId(1);
        existingImage.setTitle("Old Title");
        existingImage.setDescription("Old Description");
        existingImage.setUrl("http://example.com/old_image.jpg");

        Image updatedImage = new Image();
        updatedImage.setTitle("Updated Title");
        updatedImage.setDescription("Updated Description");
        updatedImage.setUrl("http://example.com/new_image.jpg");

        // When the service is called, return the updated image
        when(imageGalleryService.updateImage(anyInt(), any(Image.class))).thenReturn(updatedImage);

        // Convert the updated image to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(updatedImage);

        // Perform the PUT request and check the response
        mockMvc.perform(put("/api/v1/images/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.url").value("http://example.com/new_image.jpg"));
    }
}