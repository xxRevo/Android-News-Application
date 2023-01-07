package com.example.myapplication.sample_classes;

import java.io.Serializable;

public class DataListClass implements Serializable {
    private String title, text, date, categoryName, image;
    private int id;

    public DataListClass(int id, String title, String text, String date, String categoryName, String image) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.categoryName = categoryName;
        this.image = this.image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getImage() {
        return image;
    }
}
