package com.example.hit.pnt.sendotpfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txtUserInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUserInfor = findViewById(R.id.txtUserInfor);

        getDataIntent();

        setTitleToolbar();
    }

    private void getDataIntent() {
        String strPhoneNumber = getIntent().getStringExtra("phone_number");

        txtUserInfor.setText(strPhoneNumber);
    }

    private void setTitleToolbar() {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Main Activity");
        }
    }
}
