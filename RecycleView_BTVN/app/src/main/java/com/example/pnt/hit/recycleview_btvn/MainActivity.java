package com.example.pnt.hit.recycleview_btvn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ThucAnAdapter adapter = new ThucAnAdapter(initData(), getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    private List<ThucAn> initData(){
        List<ThucAn> ans = new ArrayList<>();

        ans.add(new ThucAn(R.drawable.ic_launcher_foreground, "Pizza", "Spicy Chiken Pizza", "RS 310"));
        ans.add(new ThucAn(R.drawable.ic_launcher_foreground, "Burger", "Beef Burger", "RS 350"));
        ans.add(new ThucAn(R.drawable.ic_launcher_foreground, "Pizza", "Chiken Pizza", "RS 250"));
        ans.add(new ThucAn(R.drawable.ic_launcher_foreground, "Burger", "Chiken Burger", "RS 350"));
        ans.add(new ThucAn(R.drawable.ic_launcher_foreground, "Burger", "First Burger", "RS 310"));
        ans.add(new ThucAn(R.drawable.ic_launcher_foreground, "Mango", "Mango juice", "RS 250"));
        ans.add(new ThucAn(R.drawable.ic_launcher_foreground, "Mango", "Mango juice", "RS 250"));
        ans.add(new ThucAn(R.drawable.ic_launcher_foreground, "Mango", "Mango juice", "RS 250"));
        ans.add(new ThucAn(R.drawable.ic_launcher_foreground, "Burger", "First Burger", "RS 310"));
        ans.add(new ThucAn(R.drawable.ic_launcher_foreground, "Burger", "First Burger", "RS 310"));

        return ans;
    }
}