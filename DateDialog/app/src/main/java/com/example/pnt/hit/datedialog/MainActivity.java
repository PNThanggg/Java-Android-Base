package com.example.pnt.hit.datedialog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {

    EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt1);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                chonNgay();
                chonGio();
            }
        });
    }

    private void chonNgay(){

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                // i: năm, i1: tháng, i2: ngày
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                txt.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR),
           calendar.get(Calendar.MONTH),
           calendar.get(Calendar.DATE));

        dialog.show();
    }

    private void chonGio(){
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendar.set(0, 0, 0, i, i1);
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                txt.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true);

        dialog.show();
    }
}