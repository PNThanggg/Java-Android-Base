package com.example.hit.pnt.retrofitbase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hit.pnt.retrofitbase.adapter.ItemAdapter;
import com.example.hit.pnt.retrofitbase.api.ApiServer;
import com.example.hit.pnt.retrofitbase.moedl.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> onClickButton());
    }

    private void onClickButton() {
        ApiServer.apiServer.get().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();

                items = response.body();

                if(items != null && response.isSuccessful()) {
                    ItemAdapter adapter = new ItemAdapter(items);
                    recyclerView.setHasFixedSize(true);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.addItemDecoration( new DividerItemDecoration(getApplicationContext(), layoutManager.getOrientation()));

                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}