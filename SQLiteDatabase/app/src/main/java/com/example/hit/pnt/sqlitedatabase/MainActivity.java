package com.example.hit.pnt.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_viewAll, btn_add;
    EditText ed_name, ed_age;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch sw_activeCustomer;
    RecyclerView recyclerView;

    ArrayAdapter customerAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = findViewById(R.id.button_add);
        btn_viewAll = findViewById(R.id.button_viewAll);
        ed_name = findViewById(R.id.ed_name);
        ed_age = findViewById(R.id.ed_age);
        sw_activeCustomer = findViewById(R.id.switch1);
        recyclerView = findViewById(R.id.recycleView);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        customerAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
//        recyclerView.setAdapter(customerAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerModel customerModel;

                try {
                    customerModel = new CustomerModel(-1, ed_name.getText().toString(),
                            Integer.parseInt(ed_age.getText().toString()), sw_activeCustomer.isChecked());
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error creating customer", Toast.LENGTH_LONG).show();
                    customerModel = new CustomerModel(-1, "error", 0, false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean check = dataBaseHelper.addOne(customerModel);

                customerAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
//                recyclerView.setAdapter(customerAdapter);

                Toast.makeText(MainActivity.this, "Success " + check, Toast.LENGTH_LONG).show();
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
//                recyclerView.setAdapter(customerAdapter);


//                Toast.makeText(MainActivity.this, list.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}