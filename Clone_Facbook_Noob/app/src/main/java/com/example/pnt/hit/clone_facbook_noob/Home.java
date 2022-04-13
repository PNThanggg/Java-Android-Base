package com.example.pnt.hit.clone_facbook_noob;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView email, pass;
    Button logout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        logout = findViewById(R.id.logout);

        Intent intent = getIntent();

        Acc acc = intent.getParcelableExtra("Acc");

        email.setText("Email: " + acc.getEmail());
        pass.setText("Password: " + acc.getPass());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}