package com.example.hit.pnt.retrofit;

public class Favorite {
    private int id;
    private String favorite;

    public Favorite() {
    }

    public Favorite(int id, String favorite) {
        this.id = id;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
