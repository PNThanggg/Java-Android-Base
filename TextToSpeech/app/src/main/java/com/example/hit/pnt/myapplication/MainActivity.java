package com.example.hit.pnt.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech textToSpeech;
    Button btnSpeak;
    EditText edtSpeak;
    ImageView imgSpeak;
    String toSpeak;
    static final int RESULT_SPEECH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSpeak = findViewById(R.id.btnSpeak);
        edtSpeak = findViewById(R.id.edtSpeak);
        imgSpeak = findViewById(R.id.imgSpeak);
        textToSpeech = new TextToSpeech(MainActivity.this, this::onInit);
        btnSpeak.setOnClickListener(v -> {
            toSpeak = edtSpeak.getText().toString();
            textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        });

        imgSpeak.setOnClickListener(v -> speak());
    }

    private void speak() {
        Intent intent1 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent1.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent1.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent1.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say some thing: ");

        try {
            startActivityForResult(intent1, RESULT_SPEECH);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Không thể dùng chức năng này", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_SPEECH) {
            if (resultCode == RESULT_OK && data != null) {
                List<String> hi = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                edtSpeak.setText(hi.get(0));
                textToSpeech = new TextToSpeech(MainActivity.this, MainActivity.this::onInit);
            }
        }
    }

    @Override
    protected void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onPause();
    }

    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
            textToSpeech.setLanguage(new Locale("vi"));
        } else {
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}