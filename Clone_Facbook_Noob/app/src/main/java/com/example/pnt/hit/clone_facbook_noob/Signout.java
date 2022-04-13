package com.example.pnt.hit.clone_facbook_noob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signout extends AppCompatActivity {
    EditText email, pass, confirm_pass;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signout);

        email = findViewById(R.id.email2);
        pass = findViewById(R.id.pass2);
        confirm_pass = findViewById(R.id.confirm_pass);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Acc acc = new Acc();
                acc.setEmail(email.getText().toString());
                acc.setPass(pass.getText().toString());

                Intent intent = new Intent(Signout.this, Home.class);
                intent.putExtra("Acc", (Parcelable) acc);
                startActivity(intent);
            }
        });
    }
}