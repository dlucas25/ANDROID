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
    private boolean status;
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

    public News(){}

    public void setStatus(boolean status){this.status = status;}

    public boolean isStatus() {
        return status;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
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

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }
}
