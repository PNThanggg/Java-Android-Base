package com.example.hit.pnt.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiServer {
    Gson gson = new
            GsonBuilder().
            setDateFormat("yyyy-MM-dd HH:mm:ss").
            create();

////    // https://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
////    ApiServer apiServer = new
////            Retrofit.Builder().
////            baseUrl("http://apilayer.net/"). //-> truyền vào domain
////            addConverterFactory(GsonConverterFactory.create(gson)).
////            build().
////            create(ApiServer.class);
//
//
//    @GET("api/live")
//    Call<Currency> convertUsdToVnd(@Query("access_key") String access_key,
//                                   @Query("currencies") String currencies,
//                                   @Query("source") String source,
//                                   @Query("format") int format);
//
//    @GET("api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1")
//    Call<Currency> convertUsdToVnd1();
//
//    @GET("api/live")
//    Call<Currency> convertUsdToVnd2(@QueryMap Map<String, String> options);
//
//    // https://apilayer.net/api/users/list
//    @GET("api/users/list")
//    Call<Currency> getListUser();
//
//    // https://apilayer.net/api/users/list?sort=desc
//        // có 2 cách như link đầu tiên
//
//    // https://apilayer.net/api/group/1/users -> get tất cả user trong group có id = 1 -> truyền tham số động
//    @GET("api/group/{id}/users")
//    Call<Currency> getListUserFromGroup(@Path("id") int group_id);
//
//    // https://apilayer.net/api/group/1/users/sort=desc
//    @GET("api/group/{id}/users")
//    Call<Currency> getListUserFromGroup1(@Path("id") int group_id,
//                                         @Query("sort") String sort);

    // https://jsonplaceholder.typicode.com/posts
    ApiServer apiServer = new
            Retrofit.Builder().
            baseUrl("https://jsonplaceholder.typicode.com/"). //-> truyền vào domain
            addConverterFactory(GsonConverterFactory.create(gson)).
            build().
            create(ApiServer.class);

    @POST("posts")
    Call<Post> sendPosts(@Body Post post);


}