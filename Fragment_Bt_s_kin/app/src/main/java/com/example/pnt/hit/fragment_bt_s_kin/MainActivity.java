package com.example.pnt.hit.fragment_bt_s_kin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMain = findViewById(R.id.button3);
        btMain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                FragmentA fragmentA = (FragmentA) getFragmentManager().findFragmentById(R.id.fragment_a);
                fragmentA.txtA.setText("Thay doi boi Activity");
            }
        });
    }
}