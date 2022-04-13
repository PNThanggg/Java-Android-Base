package com.example.pnt.hit.fragment1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Fragment_ extends Fragment {

    TextView output1, output2;
    Button reset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment, container, false);

        output1 = view.findViewById(R.id.output1);
        output2 = view.findViewById(R.id.output2);
        reset = view.findViewById(R.id.button2);

        return view;
    }
}
