package com.example.hit.pnt.realtimedatabasefirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextPersonName);
        Button button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        Button button1 = findViewById(R.id.button2);
        Button button2 = findViewById(R.id.button3);
        Button button3 = findViewById(R.id.button4);

        button.setOnClickListener(v -> pushDatabase());

        button1.setOnClickListener(v -> readDatabase());

        button2.setOnClickListener(v -> deleteDatabase());

        button3.setOnClickListener(v -> updateDatabase());
    }

    private void pushDatabase() {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("user_info");
//
//        User user = new User(1, "PNT", new Job(1, "Student"), "Do bt o dau day");
//        myRef.setValue(user, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(MainActivity.this, "Push data success", Toast.LENGTH_SHORT).show();
//            }
//        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("my_map");

        Map<String, Boolean> map = new HashMap<>();

        map.put("1", true);
        map.put("2", false);
        map.put("3", true);
        map.put("4", false);

        myRef.setValue(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Push data success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteDatabase() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage("Ban co chac la muon xoa ko?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("user_info/address");

                        myRef.removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(getApplicationContext(), "Delete data success", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("Cancle", null)
                .show();
    }

    private void readDatabase() {
//        // Read from the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("user_info");
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                User user = dataSnapshot.getValue(User.class);
//
//                if (user != null) {
//                    textView.setText(user.toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//            }
//        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("my_map");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Boolean> mapResult = new HashMap<>();

                for(DataSnapshot i : snapshot.getChildren()) {
                    mapResult.put(i.getKey(), i.getValue(Boolean.class));
                }

                textView.setText(mapResult.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("user_info");

//        User user = new User(2, "Test", new Job(2, "Student"), "Do bt o dau day");
//        myRef.setValue(user, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(MainActivity.this, "Update data success", Toast.LENGTH_SHORT).show();
//            }
//        });

        // ----
//        DatabaseReference myRef = database.getReference("user_info/name");
//        myRef.setValue("PNT", new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(MainActivity.this, "Update data success", Toast.LENGTH_SHORT).show();
//            }
//        });
        // --

//        DatabaseReference myRef = database.getReference("user_info");
//        myRef.child("job").child("name").setValue("ABC", new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(MainActivity.this, "Update data success", Toast.LENGTH_SHORT).show();
//            }
//        });
        // ----
//        DatabaseReference myRef = database.getReference("user_info");
//        Map<String, Object> map = new HashMap<>();
//        map.put("address", "Ko bt dau");
//        map.put("name", "ABC");
//        map.put("job/name", "job 3");
//
//        myRef.updateChildren(map, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(MainActivity.this, "Update success", Toast.LENGTH_SHORT).show();
//            }
//        });

        // --------
        DatabaseReference myRef = database.getReference("my_map");

        Map<String, Boolean> mapUpdate = new HashMap<>();
        mapUpdate.put("1", false);
        mapUpdate.put("2", false);
        mapUpdate.put("3", false);
        mapUpdate.put("4", false);

        myRef.setValue(mapUpdate, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Push data success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}