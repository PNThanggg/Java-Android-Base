package com.example.pnt.hit.list_view_base_plusplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // tạo class biểu diễn đối tượng của listview
    // custom layout hiển thị của đối tượng trên list view
    // tạo adapter

    ListView lvTraiCay;
    List<TraiCay> traiCayList;
    TraiCayAdapter adapter;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTraiCay = findViewById(R.id.listViewTraiCay);
        traiCayList = new ArrayList<>();

        traiCayList.add(new TraiCay("Dâu tây", "Dâu tây Dà Lạt", R.drawable.ic_launcher_background));
        traiCayList.add(new TraiCay("Dừa sáp", "Đặc sản Trà Vinh", R.drawable.ic_launcher_background));
        traiCayList.add(new TraiCay("Măng cụt", "Mô tả là không có mô tả gì", R.drawable.ic_launcher_background));
        traiCayList.add(new TraiCay("Thăng Long", "Mô tả là không có mô tả gì", R.drawable.ic_launcher_background));
        traiCayList.add(new TraiCay("Táo", "Táo tầu", R.drawable.ic_launcher_background));


        adapter = new TraiCayAdapter(this, R.layout.dong_trai_cay, traiCayList);
        lvTraiCay.setAdapter(adapter);
    }
}