package com.example.hit.pnt.sharedpreferencesobject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = new User(1, "Abc", "Abc, Xyz");
        DataLocalManager.setUser(user);
    }
}