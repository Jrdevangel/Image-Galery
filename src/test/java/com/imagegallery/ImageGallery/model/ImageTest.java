package com.imagegallery.ImageGallery.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    Image image = new Image();

    @Test
    void test_get_and_set_Id() {
        image.setId(1);

        int getId = image.getId();

        assertEquals(1, getId);
    }

    @Test
    void test_get_and_set_Title() {
        image.setTitle("Title");

        String getTitle = image.getTitle();

        assertEquals("Title", getTitle);
    }

    @Test
    void test_get_and_set_Description() {
        image.setDescription("Description");

        String getDescription = image.getDescription();

        assertEquals("Description", getDescription);
    }

    @Test
    void test_get_and_set_Url() {
        image.setUrl("url");

        String getUrl = image.getUrl();

        assertEquals("url", getUrl);
    }

}