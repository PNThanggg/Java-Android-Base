package com.example.pnt.hit.livedate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button button;

    private UserAdapter adapter;
    private UserViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv_user);
        button = findViewById(R.id.add_user);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        model = new ViewModelProvider(this).get(UserViewModel.class);
        model.getMutableLiveData().observe(this, users -> {
            adapter = new UserAdapter(users);
            recyclerView.setAdapter(adapter);
        });

        button.setOnClickListener(view -> {
            addUser();
        });
    }

    private void addUser() {
        User user = new User(R.mipmap.ic_launcher, "anbc", "abc");
        model.addUser(user);
    }
}