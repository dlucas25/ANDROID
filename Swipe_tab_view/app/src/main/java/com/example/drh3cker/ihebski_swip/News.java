package com.example.drh3cker.ihebski_swip;

/**
 * Created by Administrador on 11/11/2015.
 */
public class News {
    private int image;
    private String title;
    private String description;
    int id;

    public News(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
