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

public class FragmentB extends Fragment {

    EditText edtB;
    Button btB;
    TextView txtB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragmenty_b, container, false);

        txtB = view.findViewById(R.id.textView2);
        edtB = view.findViewById(R.id.edittext);
        btB = view.findViewById(R.id.button2);

        btB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), edtB.getText().toString(), Toast.LENGTH_LONG).show();
                TextView txtA = getActivity().findViewById(R.id.textView);
                txtA.setText(edtB.getText().toString());
            }
        });

        return view;
    }
}
