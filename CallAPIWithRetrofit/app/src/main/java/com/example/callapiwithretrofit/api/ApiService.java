package com.example.callapiwithretrofit.api;

import com.example.callapiwithretrofit.model.Account;
import com.example.callapiwithretrofit.model.AccountDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String baseUrl ="https://demo-b5.herokuapp.com/";
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    //@Query("key") String key truyền param
    //@Multipart annotation dành cho API hỗ trợ data up lên dạng multipart/form data
    //@Part("key") Requestbody value up từng thuộc tính lên
    //@Part MultiPartBody.Part avt up file lên server

    @GET("api/accounts")
    Call<List<Account>> getAllAccount();

    @POST("api/accounts")
    Call<Account> createAccount(@Body Account account);

    @PATCH("api/accounts/{id}")
    Call<Account> updateAccount(@Path("id") int id, @Body AccountDTO accountDTO);

    @DELETE("api/accounts/{id}")
    Call<Account> deleteAccount(@Path("id") int id);
}
