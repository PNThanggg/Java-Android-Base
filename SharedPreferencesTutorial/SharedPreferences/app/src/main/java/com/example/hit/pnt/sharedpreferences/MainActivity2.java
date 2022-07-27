package com.example.hit.pnt.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView = findViewById(R.id.textView);
        Set<String> set = new HashSet<>();

        set = DataLocalManager.getNameUsers();

        textView.setText(set.toString());
    }
}