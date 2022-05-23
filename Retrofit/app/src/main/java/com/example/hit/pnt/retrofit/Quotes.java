package com.example.hit.pnt.retrofit;

import com.google.gson.annotations.SerializedName;

public class Quotes {

    @SerializedName("USDVND")
    private float usdVnd;

    public Quotes() {
    }

    public Quotes(float usdVnd) {
        this.usdVnd = usdVnd;
    }

    public float getUsdVnd() {
        return usdVnd;
    }

    public void setUsdVnd(float usdVnd) {
        this.usdVnd = usdVnd;
    }
}
