package com.example.hit.pnt.room;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hit.pnt.room.adapter.UserAdapter;
import com.example.hit.pnt.room.database.AppDatabase;
import com.example.hit.pnt.room.database.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Thêm thư viện:
//    implementation 'androidx.recyclerview:recyclerview:1.2.1'
//    annotationProcessor 'androidx.room:room-compiler:2.4.2'
//    implementation 'androidx.room:room-runtime:2.4.2'

    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addNewUser = findViewById(R.id.addNewUser);
        addNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityIfNeeded(
                        new Intent(MainActivity.this, AddActivity.class),
                        100);
            }
        });

        initRecyclerView();

        loadAllUser();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new UserAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void loadAllUser() {
        AppDatabase database = AppDatabase.getDatabaseInstance(this.getApplicationContext());

        List<User> list = database.userDao().getAllUsers();

        adapter.setUserList(list);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100) {
            loadAllUser();
        }
    }
}