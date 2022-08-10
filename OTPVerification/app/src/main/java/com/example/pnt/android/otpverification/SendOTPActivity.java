package com.example.pnt.android.otpverification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pnt.android.otpverification.databinding.ActivitySendOtpBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {

    ActivitySendOtpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonGetOTP.setOnClickListener(v -> {
            if (binding.inputMobile.getText().toString().trim().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter Mobile", Toast.LENGTH_LONG).show();
                return;
            }

            binding.processBar.setVisibility(View.VISIBLE);
            binding.buttonGetOTP.setVisibility(View.INVISIBLE);

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+84" + binding.inputMobile.getText().toString(),
                    60,
                    TimeUnit.SECONDS,
                    SendOTPActivity.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            binding.processBar.setVisibility(View.GONE);
                            binding.buttonGetOTP.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            binding.processBar.setVisibility(View.GONE);
                            binding.buttonGetOTP.setVisibility(View.VISIBLE);
                            Toast.makeText(SendOTPActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            binding.processBar.setVisibility(View.GONE);
                            binding.buttonGetOTP.setVisibility(View.VISIBLE);

                            Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                            intent.putExtra("mobile", binding.inputMobile.getText().toString());
                            intent.putExtra("verificationId", verificationId);
                            startActivity(intent);
                        }
                    }
            );
        });
    }
}