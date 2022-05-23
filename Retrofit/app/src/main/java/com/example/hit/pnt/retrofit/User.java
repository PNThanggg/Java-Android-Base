package com.example.hit.pnt.retrofit;

import java.util.List;

public class User {
    private int id;
    private String name;
    private boolean isActive;
    private Job job;
    List<Favorite> favorites;

    public User() {
    }

    public User(int id, String name, boolean isActive, Job job, List<Favorite> favorites) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.job = job;
        this.favorites = favorites;
    }

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }
}
