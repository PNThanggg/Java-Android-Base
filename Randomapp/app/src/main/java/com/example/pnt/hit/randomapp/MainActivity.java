package com.example.pnt.hit.randomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText txt1, txt2;
    Button bt;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = findViewById(R.id.edt1);
        txt2 = findViewById(R.id.edt2);
        bt = findViewById(R.id.bt);
        tv = findViewById(R.id.tv1);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = txt1.getText().toString();
                String str2 = txt2.getText().toString();

                if(str1.equals("") || str2.equals("")){
                    Toast.makeText(MainActivity.this, "Nhap so", Toast.LENGTH_LONG).show();
                } else {
                    Integer number1 = Integer.parseInt(str1);
                    Integer number2 = Integer.parseInt(str2);
                    Random random = new Random();
                    Integer number = random.nextInt(Math.abs(number2 - number1 + 1)) + Math.min(number1, number2);
                    tv.setText(number.toString());
                }
            }
        });
    }
}