package com.example.pnt.hit.recyclerview_base;

public class DataShop {
    private String name;
    private Integer img;

    public DataShop() {
    }

    public DataShop(String name, Integer img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }
}
