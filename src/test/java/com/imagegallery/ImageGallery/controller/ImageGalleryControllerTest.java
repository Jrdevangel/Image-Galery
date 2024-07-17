package com.imagegallery.ImageGallery.controller;

import com.imagegallery.ImageGallery.services.ImageGalleryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void testDeleteImage() throws Exception {
        Integer imageId = 1;

        doNothing().when(imageGalleryService).deleteImage(imageId);

        mockMvc.perform(delete("/api/v1/images/{id}", imageId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(imageGalleryService).deleteImage(imageId);
    }
}
