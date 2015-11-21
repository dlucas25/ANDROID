package com.example.drh3cker.ihebski_swip;

/**
 * Created by Administrador on 11/11/2015.
 */
public class News {
    private String image;
    private String title;
    private String description;
    int id;

    public News(String image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
