package com.example.pnt.hit.list_view_base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // listview: hiển thị theo dạng danh sách
    // B1: tạo 1 list view ở phần layout
    // B2: tạo 1 danh sách chứa dữ liệu để hiện thị
    // B3: tạo 1 arrayadapter dung để hiện thị dữ liệu ở bước 2 ra listview
    // B4: hiện thị

    ListView lvMonHoc;
    List<String> listMonHoc;
    EditText editText;
    Button button, button2;

    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMonHoc = findViewById(R.id.listViewMonHoc);
        editText = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);


        listMonHoc = new ArrayList<>();
        listMonHoc.add("Android");
        listMonHoc.add("PHP");
        listMonHoc.add("IOS");
        listMonHoc.add("C#");
        listMonHoc.add("C/C++");

        // Khởi tạo adapter
        // 3 tham số:
        // 1: màn hình hiển thị -> MainActivity,this
        // 2: dạng layout đổ ra
        // 3: dữ liệu đổ vào để hiện thị ra list view
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1, listMonHoc);

        lvMonHoc.setAdapter(adapter);

        // chạm
        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // i: trả về vị trí click trên list view: start index = 0
                Toast.makeText(MainActivity.this, listMonHoc.get(i), Toast.LENGTH_LONG).show();
            }
        });

//        // giữ
//        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, "Long click: " + i, Toast.LENGTH_LONG).show();
//
//                return false;
//            }
//        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monHoc = editText.getText().toString();
                listMonHoc.add(monHoc);
                adapter.notifyDataSetChanged(); // khi dữ liêu của list thay đổi thì cập nhậ lại adapte
            }
        });

        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editText.setText(listMonHoc.get(i));
                index = i;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listMonHoc.set(index, editText.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                listMonHoc.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}