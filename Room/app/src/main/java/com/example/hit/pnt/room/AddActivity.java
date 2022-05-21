package com.example.hit.pnt.room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hit.pnt.room.database.AppDatabase;
import com.example.hit.pnt.room.database.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText FirstNameInput = findViewById(R.id.enterFirstName);
        EditText LastNameInput = findViewById(R.id.enterLastName);
        FloatingActionButton save = findViewById(R.id.saveButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewUser(FirstNameInput.getText().toString(),
                        LastNameInput.getText().toString());
            }
        });
    }

    private void saveNewUser(String firstName, String lastName) {
        AppDatabase database = AppDatabase.getDatabaseInstance(this.getApplicationContext());

        User user = new User();
        user.first_name = firstName;
        user.last_name = lastName;

        database.userDao().insertUser(user);

        finish();
    }
}