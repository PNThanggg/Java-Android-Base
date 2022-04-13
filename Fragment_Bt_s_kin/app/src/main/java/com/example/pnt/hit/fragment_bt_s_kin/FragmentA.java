package com.example.pnt.hit.fragment_bt_s_kin;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class FragmentA extends Fragment {

    TextView txtA;
    EditText edtA;
    Button btA;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_a, container, false);

        txtA = view.findViewById(R.id.textView);
        edtA = view.findViewById(R.id.edittext);
        btA = view.findViewById(R.id.button);

        btA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), edtA.getText().toString(), Toast.LENGTH_LONG).show();
                TextView txtMain = getActivity().findViewById(R.id.textView3);
                txtMain.setText(edtA.getText().toString());
            }
        });

        return view;
    }
}
