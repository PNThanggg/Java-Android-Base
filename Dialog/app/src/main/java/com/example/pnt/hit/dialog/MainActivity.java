package com.example.pnt.hit.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrName;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        arrName = new ArrayList<>();
        AddArrayName();

        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, arrName);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                XacNhanXoa(i);
                return false;
            }
        });
    }

    private void XacNhanXoa(int pos){
        AlertDialog.Builder alterDialog = new AlertDialog.Builder(this);
        alterDialog.setTitle("Thông báo");
        alterDialog.setMessage("Có muốn xoá hay không?");

        alterDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                arrName.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });

        alterDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alterDialog.setIcon(R.drawable.ic_launcher_foreground);
        alterDialog.show();
    }
    
    private void AddArrayName(){
        arrName.add("Android");
        arrName.add("iOS");
        arrName.add("PHP");
        arrName.add("APS.Net");
        arrName.add("iOS");
        arrName.add("NodeJS");
        arrName.add("NodeJS");
        arrName.add("NodeJS");
    }
}