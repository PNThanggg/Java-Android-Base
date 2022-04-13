package com.example.pnt.hit.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {

    Button button, button1;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button); // 3
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowMenu();
            }
        });

        constraintLayout = findViewById(R.id.constraintLayout);
        button1 = findViewById(R.id.button1);

        // đăng ký view cho context menu
        // context menu là long click
        registerForContextMenu(button1);
    }

    // popup menu
    private void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, button); // ở  3
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menuThem:
                        break;

                    case R.id.menuXoa:
                        break;

                    case R.id.menuSua:
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 1
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //popup menu
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 2
        switch (item.getItemId()) {
            case R.id.menu_setting:
                break;

            case R.id.menu_share:
                break;

            case R.id.menu_email:
                break;

            case R.id.menu_phone:
                break;

            case R.id.menu_search:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    // Context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);

        menu.setHeaderIcon(R.drawable.ic_launcher_foreground);
        menu.setHeaderTitle("Chọn màu");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuVang:
                constraintLayout.setBackgroundColor(Color.YELLOW);
                break;

            case R.id.menuDo:
                constraintLayout.setBackgroundColor(Color.RED);
                break;

            case R.id.menuXanh:
                constraintLayout.setBackgroundColor(Color.GREEN);
                break;
        }

        return super.onContextItemSelected(item);
    }
}