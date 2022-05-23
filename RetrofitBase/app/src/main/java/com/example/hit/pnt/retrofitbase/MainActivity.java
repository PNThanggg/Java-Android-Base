package com.example.hit.pnt.retrofitbase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hit.pnt.retrofitbase.api.ApiServer;
import com.example.hit.pnt.retrofitbase.moedl.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtId, txtUserId, txtBody, txtTitle;
    private Button button;
    private EditText inputID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtUserId = findViewById(R.id.txtUserId);
        txtId = findViewById(R.id.txtId);
        txtBody = findViewById(R.id.txtBody);
        txtTitle = findViewById(R.id.txtTitle);
        inputID = findViewById(R.id.inputId);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton();
            }
        });
    }

    private void onClickButton() {
        if(inputID.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Input ID", Toast.LENGTH_SHORT).show();
        } else {
            ApiServer.apiServer.getId(Integer.parseInt(inputID.getText().toString())).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();

                    User user = response.body();
                    if(user != null) {
                        txtId.setText(String.valueOf(user.getId()));
                        txtUserId.setText(String.valueOf(user.getUserId()));
                        txtTitle.setText(user.getTitle());
                        txtBody.setText(user.getBody());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}