package com.example.pnt.hit.fragment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input1, input2;
    Button getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);

        getData = findViewById(R.id.button);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ fragment = (Fragment_) getFragmentManager().findFragmentById(R.id.fragment);
                fragment.output1.setText(input1.getText().toString());
                fragment.output2.setText(input2.getText().toString());
                fragment.reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fragment.output1.setText("");
                        fragment.output2.setText("");
                        input1.setText("");
                        input2.setText("");
                    }
                });
            }
        });
    }
}