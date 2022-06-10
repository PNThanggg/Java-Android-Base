package com.example.hit.pnt.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // add thư viện: implementation 'com.google.code.gson:gson:2.8.9'
                    // implementation 'com.squareup.retrofit2:retrofit:2.1.0'
                    // implementation 'com.squareup.retrofit2:converter-gson:2.1.0'

    // cho app kết nối Internet: manifests -> <uses-permission android:name="android.permission.INTERNET"/>
    // manifests -> application -> android:usesCleartextTraffic="True"

    // link retrofit: https://square.github.io/retrofit/

    private TextView txtTerms;
    private TextView txtSource;
    private TextView txtUsdVnd;
    private Button btCallAPI;
    private TextView txtPostResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Job job = new Job(1, "Coder");
//        List<Favorite> lists = new ArrayList<>();
//        lists.add(new Favorite(1, "Music"));
//        lists.add(new Favorite(2, "Film"));
//        lists.add(new Favorite(3, "Read book"));
//
//        User user = new User(1, "PNT", true, job, lists);
//
//        Gson gson = new Gson();
//        String strJson = gson.toJson(user);
//        System.out.println(strJson);

        txtTerms = findViewById(R.id.txt1);
        txtSource = findViewById(R.id.txt2);
        txtUsdVnd = findViewById(R.id.txt3);

        btCallAPI = findViewById(R.id.btCallAPI);
        btCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonCallAPI();
//                sendPost();
            }
        });

        txtPostResult = findViewById(R.id.txtPostResult);
    }

    private void onClickButtonCallAPI() {
        // https://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
//        ApiServer.apiServer.convertUsdToVnd("843d4d34ae72b3882e3db642c51e28e6",
//                "VND", "USD", 1).enqueue(new Callback<Currency>() {
//            @Override
//            public void onResponse(Call<Currency> call, Response<Currency> response) {
//                // call thành công
//                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
//
//                Currency currency = response.body();
//
//                if (currency != null && currency.isSuccess()) {
//                    txtTerms.setText(currency.getTerms());
//                    txtSource.setText(currency.getSource());
//                    txtUsdVnd.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Currency> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
//            }
//        });

//        ApiServer.apiServer.convertUsdToVnd1().enqueue(new Callback<Currency>() {
//            @Override
//            public void onResponse(Call<Currency> call, Response<Currency> response) {
//                // call thành công
//                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
//
//                Currency currency = response.body();
//
//                if (currency != null && currency.isSuccess()) {
//                    txtTerms.setText(currency.getTerms());
//                    txtSource.setText(currency.getSource());
//                    txtUsdVnd.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
//                }
//            }

        Map<String, String> options = new HashMap<>();
        options.put("access_key", "843d4d34ae72b3882e3db642c51e28e6");
        options.put("currencies", "VND");
        options.put("source", "USD");
        options.put("format", "1");

        ApiServer.apiServer.convertUsdToVnd2(options).enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                // call thành công
                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();

                Currency currency = response.body();

                if (currency != null && currency.isSuccess()) {
                    txtTerms.setText(currency.getTerms());
                    txtSource.setText(currency.getSource());
                    txtUsdVnd.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendPost() {
        Post post = new Post(10, 101, "PNT", "PNT-HIT");

        ApiServer.apiServer.sendPosts(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();

                Post postResult = response.body();

                if(postResult != null) {
                    txtPostResult.setText(postResult.toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}