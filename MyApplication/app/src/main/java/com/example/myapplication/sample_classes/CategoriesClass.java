package com.example.myapplication.sample_classes;

public class CategoriesClass {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoriesClass(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
