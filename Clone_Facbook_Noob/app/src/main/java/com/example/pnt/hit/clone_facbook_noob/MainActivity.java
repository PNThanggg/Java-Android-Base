package com.example.pnt.hit.clone_facbook_noob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText pass, email;
    Button login;
    TextView signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pass = findViewById(R.id.password1);
        email = findViewById(R.id.email1);
        login = findViewById(R.id.login);
        signout = findViewById(R.id.signout);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Home.class);
                Acc acc = new Acc(email.getText().toString(), pass.getText().toString());
                intent.putExtra("Acc",(Parcelable) acc);
                startActivity(intent);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Signout.class);
                startActivity(intent);
            }
        });
    }
}