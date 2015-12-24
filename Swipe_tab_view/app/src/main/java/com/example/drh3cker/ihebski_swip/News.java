package com.example.drh3cker.ihebski_swip;

/**
 * Created by Administrador on 11/11/2015.
 */
public class News {
    private String image;
    private String title;
    private String description;
    private String category;
    private String date;
    int id;

    public News(int id,String category, String title,String date,String description,String image) { //Noticias
        this.image = image;
        this.title = title;
        this.description = description;
        this.category=category;
        this.date=date;
        this.id = id;
    }
    public News(int id,String title,String date,String description,String image) { //Eventos
        this.image = image;
        this.title = title;
        this.description = description;
        this.date=date;
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
