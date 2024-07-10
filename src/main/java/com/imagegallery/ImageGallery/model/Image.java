package com.imagegallery.ImageGallery.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "title")
    private String title;
    @Column (name = "description")
    private String description;
    @Column (name = "url")
    private String url;
}
