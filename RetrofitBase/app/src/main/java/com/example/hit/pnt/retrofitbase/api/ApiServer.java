package com.example.hit.pnt.retrofitbase.api;

import com.example.hit.pnt.retrofitbase.moedl.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiServer {
    Gson gson = new
            GsonBuilder().
            setDateFormat("yyyy-MM-dd HH:mm:ss").
            create();

    ApiServer apiServer = new
            Retrofit.Builder().
            baseUrl("https://jsonplaceholder.typicode.com/").
            addConverterFactory(GsonConverterFactory.create(gson)).
            build().
            create(ApiServer.class);

    @GET("posts")
    Call<List<Item>> get();
}
