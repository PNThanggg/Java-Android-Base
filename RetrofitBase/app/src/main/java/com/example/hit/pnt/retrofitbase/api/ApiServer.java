package com.example.hit.pnt.retrofitbase.api;

import com.example.hit.pnt.retrofitbase.moedl.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @GET("posts/{id}")
    Call<User> getId(@Path("id") int id);
}
