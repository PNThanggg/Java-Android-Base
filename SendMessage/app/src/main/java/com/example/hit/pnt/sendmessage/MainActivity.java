package com.example.hit.pnt.sendmessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputPhone, inputMessage;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputPhone = findViewById(R.id.inputPhone);
        inputMessage = findViewById(R.id.inputMessage);

        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    sendMessage();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.SEND_SMS},
                            100);
                }
            }
        });
    }

    private void sendMessage() {
        String phone = inputPhone.getText().toString().trim();
        String message = inputMessage.getText().toString().trim();

        if(!phone.equals("") && !message.equals("")) {
            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(phone, null, message, null, null);
            Toast.makeText(MainActivity.this, "SMS sent successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Enter values first.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100 && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendMessage();
        } else {
            Toast.makeText(MainActivity.this, "Permission Denied!", Toast.LENGTH_LONG).show();
        }
    }
}