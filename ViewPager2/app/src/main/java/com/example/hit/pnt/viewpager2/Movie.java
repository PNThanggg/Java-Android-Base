package com.example.hit.pnt.viewpager2;

public class Movie {
    private String name, category, releaseDate;
    private int poster;
    private float rating;

    public Movie() {
    }

    public Movie(String name, String category, String releaseDate, int poster, float rating) {
        this.name = name;
        this.category = category;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
