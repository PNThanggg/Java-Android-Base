package com.example.hit.pnt.sendotpfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import org.w3c.dom.Text;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class EnterOTPActivity extends AppCompatActivity {

    private static final String TAG = EnterOTPActivity.class.getName();


    private EditText inputOPTCode;
    private Button sendOTPCode;
    private TextView sendAgainOTP;

    private FirebaseAuth mAuth;
    private String mPhoneNumber;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mForceResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otpactivity);

        getDataIntent();

        setTitleToolbar();

        inputOPTCode = findViewById(R.id.inputOTPCode);
        sendOTPCode = findViewById(R.id.btnSendOTPCode);
        sendAgainOTP = findViewById(R.id.textSendOPTAgain);

        mAuth = FirebaseAuth.getInstance(FirebaseApp.initializeApp(this));

        sendOTPCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOTPCode = inputOPTCode.getText().toString().trim();
                onClickSendOTPCode(strOTPCode);
            }
        });

        sendAgainOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTPAgain();
            }
        });
    }

    private void getDataIntent() {
        mPhoneNumber = getIntent().getStringExtra("phone_number");
        mVerificationId = getIntent().getStringExtra("verification_id");
    }

    private void setTitleToolbar() {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Enter OTP Code");
        }
    }

    private void onClickSendOTPCode(String strOTPCode) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, strOTPCode);

        signInWithPhoneAuthCredential(credential);
    }

    private void sendOTPAgain() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)  // -> truyền vào 1 Firebast Auth
                        .setPhoneNumber(mPhoneNumber) // -> truyền vào  số điện thoại
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setForceResendingToken(mForceResendingToken)
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
                                Toast.makeText(EnterOTPActivity.this,
                                        "Verification Failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);

                                mVerificationId = verificationId;
                                mForceResendingToken = forceResendingToken;
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
                                Toast.makeText(EnterOTPActivity.this,
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
}