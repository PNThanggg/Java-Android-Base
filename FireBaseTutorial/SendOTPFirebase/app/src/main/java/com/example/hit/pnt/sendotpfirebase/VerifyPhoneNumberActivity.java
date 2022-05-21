package com.example.hit.pnt.sendotpfirebase;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNumberActivity extends AppCompatActivity {

    private static final String TAG = VerifyPhoneNumberActivity.class.getName();

    private EditText inputPhoneNumber;
    private Button verifyPhoneNumber;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_number);

        setTitleToolbar();

        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        verifyPhoneNumber = findViewById(R.id.btnVerifyPhoneNumber);

        mAuth = FirebaseAuth.getInstance();

        verifyPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputPhoneNumber.getText().toString().length() < 10) {
                    Toast.makeText(VerifyPhoneNumberActivity.this, "SDT sai", Toast.LENGTH_SHORT).show();
                } else {
                    String strPhoneNumber = inputPhoneNumber.getText().toString().trim();
                    onClickVerifyPhoneNumber(strPhoneNumber);
                }
            }
        });
    }

    private void setTitleToolbar() {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Verify Phone Number");
        }
    }

    private void onClickVerifyPhoneNumber(String str) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)  // -> truyền vào 1 Firebast Auth
                        .setPhoneNumber(str) // -> truyền vào  số điện thoại
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                // Được gọi trong 2 TH:
                                // 1: Xác minh được ngay tức thi
                                // 2: Tự động truy xuất: google play có thể tự động phát hiện SMS xác
                                // minh và tự động xác minh mà ko cần hành động của người dùng

                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(VerifyPhoneNumberActivity.this,
                                        "Verification Failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);

                                gotoEnterOTPActivity(str, verificationId);
                            }
                        })
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI

                            gotoMainActivity(user.getPhoneNumber());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(VerifyPhoneNumberActivity.this,
                                        "The verification code enter was invalid ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void gotoMainActivity(String str) {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("phone_number", str);

        startActivity(intent);
    }

    private void gotoEnterOTPActivity(String str, String verificationId) {
        Intent intent = new Intent(this, EnterOTPActivity.class);

        intent.putExtra("phone_number", str);
        intent.putExtra("verificationID", verificationId);

        startActivity(intent);
    }
}