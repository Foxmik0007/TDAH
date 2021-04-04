package com.volanadesign.tdahproject;

public class Task {
    private String name;
    private String Description;
    private int picture;

    public Task() {
    }

    public Task(String name, String description, int picture) {
        this.name = name;
        Description = description;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
