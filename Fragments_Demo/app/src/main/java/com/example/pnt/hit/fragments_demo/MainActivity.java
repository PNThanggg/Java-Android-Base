package com.example.pnt.hit.fragments_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;

        switch (view.getId()) {
            case R.id.button1:
                fragment = new FragmentA();
                break;

            case R.id.button2:
                fragment = new FlagmentB();
                break;
        }

        // dùng add thì sẽ bị lệch nếu các flagment ko có cùng layout
        // nên dùng replace
//        fragmentTransaction.add(R.id.fragment, fragment);
        fragmentTransaction.replace(R.id.fragment, fragment);

        // vì .commit chỉ gọi đc 1 lần nên dùng cách này
        fragmentTransaction.commit();
    }
}