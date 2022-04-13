package com.example.pnt.hit.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {

    TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTime = findViewById(R.id.txtTime);

        Calendar calendar = Calendar.getInstance(); // khởi tạo calendar
//        txtTime.setText(calendar.getTime() + "");
        txtTime.append(calendar.getTime() + "\n");
        txtTime.append(calendar.getTime() + "\n");
        txtTime.append(calendar.get(Calendar.DATE) + "\n");
        txtTime.append(calendar.get(Calendar.MONDAY) + "\n");
        txtTime.append(calendar.get(Calendar.YEAR) + "\n");


        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");

        txtTime.append(dinhDangNgay.format(calendar.getTime()));
    }

}