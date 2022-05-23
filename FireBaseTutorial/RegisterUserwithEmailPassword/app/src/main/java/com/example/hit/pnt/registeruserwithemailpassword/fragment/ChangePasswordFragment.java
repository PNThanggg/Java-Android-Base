package com.example.hit.pnt.registeruserwithemailpassword.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hit.pnt.registeruserwithemailpassword.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordFragment extends Fragment {

    private View view;
    private EditText edtNewPassword, edtConfirmNewPassword;
    private Button btChangePassword;
    private ProgressDialog dialog;

    public ChangePasswordFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_password, container, false);

        dialog = new ProgressDialog(getActivity());

        edtNewPassword = view.findViewById(R.id.inputNewPassword);
        edtConfirmNewPassword = view.findViewById(R.id.inputConfirmNewPassword);

        btChangePassword = view.findViewById(R.id.changePassword);
        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChangePassword();
            }
        });

        return view;
    }

    private void onClickChangePassword() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String newPassword = edtNewPassword.getText().toString().trim();
        String confirmNewPassword = edtConfirmNewPassword.getText().toString().trim();

        if(newPassword.equals(confirmNewPassword)) {
            user.updatePassword(newPassword).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialog.dismiss();

                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "User's password updated", Toast.LENGTH_SHORT).show();
                            } else {
                                // show dialog reAuthenticate
                                dialogReAuthenticate();
                            }
                        }
                    });
        } else {
            Toast.makeText(getActivity(), "Input password again", Toast.LENGTH_SHORT).show();
        }
    }

    private void reAuthenticate(String strEmail, String strPassword) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential = EmailAuthProvider
                .getCredential(strEmail, strPassword);

        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            onClickChangePassword();
                        } else {
                            Toast.makeText(getActivity(), "Please enter again email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void dialogReAuthenticate() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialong_re_authenticate);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        if(window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        EditText dialogEmail, dialogPassword;
        Button btCancel, btSubmit;

        dialogEmail = dialog.findViewById(R.id.dialogEmail);
        dialogPassword = dialog.findViewById(R.id.dialogPassword);

        String strEmail = dialogEmail.getText().toString().trim();
        String strPassword = dialogPassword.getText().toString().trim();

        btSubmit = dialog.findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strEmail.isEmpty() || strPassword.isEmpty()) {
                    Toast.makeText(getActivity(), "Input fully information", Toast.LENGTH_SHORT).show();
                } else {
                    reAuthenticate(strEmail, strPassword);
                }
            }
        });

        btCancel = dialog.findViewById(R.id.btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
}