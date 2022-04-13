package com.example.pnt.hit.recycleview_btvn;

public class ThucAn {
    private Integer imgHinh;
    private String ten, moTa, gia;

    public ThucAn() {
    }

    public ThucAn(Integer imgHinh, String ten, String moTa, String gia) {
        this.imgHinh = imgHinh;
        this.ten = ten;
        this.moTa = moTa;
        this.gia = gia;
    }

    public Integer getImgHinh() {
        return imgHinh;
    }

    public void setImgHinh(Integer imgHinh) {
        this.imgHinh = imgHinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
