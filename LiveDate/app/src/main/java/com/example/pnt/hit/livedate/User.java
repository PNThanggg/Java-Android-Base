package com.example.pnt.hit.livedate;

public class User {
    private int imageAvatar;
    private String name, description;

    public User() {
    }

    public User(int imageAvatar, String name, String description) {
        this.imageAvatar = imageAvatar;
        this.name = name;
        this.description = description;
    }

    public int getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(int imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
