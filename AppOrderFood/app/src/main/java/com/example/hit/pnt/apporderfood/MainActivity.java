package com.example.hit.pnt.apporderfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hit.pnt.apporderfood.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                                    LinearLayoutManager.HORIZONTAL, false);

        recyclerViewCategoryList = findViewById(R.id.recycleView1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        List<Category> list = new ArrayList<>();
        list.add(new Category("Pizza", "cat_1"));
        list.add(new Category("Burger", "cat_2"));
        list.add(new Category("Hot dog", "cat_3"));
        list.add(new Category("Drink", "cat_4"));
        list.add(new Category("Donut", "cat_5"));
    }
}