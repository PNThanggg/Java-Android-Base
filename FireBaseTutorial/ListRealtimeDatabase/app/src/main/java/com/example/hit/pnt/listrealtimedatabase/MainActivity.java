package com.example.hit.pnt.listrealtimedatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtName;
    private Button button;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = findViewById(R.id.txtId);
        edtName = findViewById(R.id.txtName);
        button = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recycleView);
        list = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edtId.getText().toString());
                String name = edtName.getText().toString();
                User user = new User(id, name);
                addUser(user);

//                addAllUser();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new Adapter(list, new Adapter.IClickListener() {
            @Override
            public void onClickUpdateItem(User user) {
                openDialogUpdateItem(user);
            }
        });

        recyclerView.setAdapter(adapter);

        getListUserFromRealtimeDatabase();
    }

    private void addUser(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_users");

        String pathObject = String.valueOf(user.getId());
        myRef.child(pathObject).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Add User Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addAllUser() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_users");

        List<User> mList = new ArrayList<>();
        mList.add(new User(1, "User 1"));
        mList.add(new User(2, "User 2"));
        mList.add(new User(3, "User 3"));

        myRef.setValue(mList, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Add All User Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getListUserFromRealtimeDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_users");

//        myRef.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(list != null) list.clear();
//
//                for(DataSnapshot i : snapshot.getChildren()) {
//                    User user = i.getValue(User.class);
//                    list.add(user);
//                }
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "Get list users faild", Toast.LENGTH_SHORT).show();
//            }
//        });

         myRef.addChildEventListener(new ChildEventListener() {
             @SuppressLint("NotifyDataSetChanged")
             @Override
             public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                 User user = snapshot.getValue(User.class);
                 if(user != null) {
                     list.add(user);
                     adapter.notifyDataSetChanged();
                 }
             }

             @SuppressLint("NotifyDataSetChanged")
             @Override
             public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                 User user = snapshot.getValue(User.class);

                 if(list == null || list.isEmpty() || user == null) return;

                 for(int i = 0; i < list.size(); i++) {
                     if(list.get(i).getId().equals(user.getId())) list.set(i, user);
                 }

                 adapter.notifyDataSetChanged();
             }

             @Override
             public void onChildRemoved(@NonNull DataSnapshot snapshot) {

             }

             @Override
             public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
    }

    private void openDialogUpdateItem(User user) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        EditText editText = dialog.findViewById(R.id.editext);
        Button btCancel = dialog.findViewById(R.id.btCancel);
        Button btUpdate = dialog.findViewById(R.id.btUpdate);

        editText.setText(user.getName());

        btCancel.setOnClickListener(v -> dialog.dismiss());
        btUpdate.setOnClickListener(v -> {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("list_users");

            user.setName(editText.getText().toString());
            editText.setSelection(editText.getText().toString().length());

            myRef.child(String.valueOf(user.getId())).updateChildren(user.toMap(), new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    Toast.makeText(MainActivity.this, "Update data success", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        });

        dialog.show();
    }
}