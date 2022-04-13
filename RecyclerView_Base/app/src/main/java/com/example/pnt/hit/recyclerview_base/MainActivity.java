package com.example.pnt.hit.recyclerview_base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView(){
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true); // tối ưu hoá dữ liệu không bị ảnh hưởng bởi nội dung trong Adapter

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        List<DataShop> list = new ArrayList<>();
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));
        list.add(new DataShop("123", R.drawable.ic_launcher_foreground));

        ShopAdapter adapter = new ShopAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}