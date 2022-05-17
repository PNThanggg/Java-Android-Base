package com.example.hit.pnt.notificationbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendNotification = findViewById(R.id.button1);
        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        Button sendNotification2 = findViewById(R.id.button2);
        sendNotification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send2();
            }
        });
    }

//    private void send() {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//
//        Notification notification = new NotificationCompat.Builder(this, MyNotification.CHANNEL_ID_1)
//                .setContentTitle("Title push notification channel 1")
//                .setContentText("Message push notification channel 2")
//                .setSmallIcon(R.drawable.ic_notifications)
//                .setLargeIcon(bitmap)
//                .build();
//
//        NotificationManagerCompat.from(this).notify(getNotificationId(), notification);
//    }

    private void send() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification notification = new NotificationCompat.Builder(this, MyNotification.CHANNEL_ID_1)
                .setContentTitle("Title push notification channel 1")
                .setContentText("Message push notification channel 2")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("abc")) // tạo notification với text dài
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null)) // tạo notification hiện thị ảnh lớn
                .setSound(uri) // tạo âm thanh khi nhận thông báo
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT) ->
//                .setPriority(NotificationCompat.PRIORITY_MAX)
//                .setPriority(NotificationCompat.PRIORITY_MIN) -> ko nhận âm thanh
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_notifications)
                .setLargeIcon(bitmap)
                .build();

        NotificationManagerCompat.from(this).notify(getNotificationId(), notification);
    }

    private void send2() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Notification notification = new NotificationCompat.Builder(this, MyNotification.CHANNEL_ID_2)
                .setContentTitle("Title push notification channel 2")
                .setContentText("Message push notification channel 2")
                .setSmallIcon(R.drawable.ic_notifications)
                .setLargeIcon(bitmap)
                .build();

        NotificationManagerCompat.from(this).notify(getNotificationId(), notification);
    }


    private int getNotificationId() {
        return (int) new Date().getTime();
    }
}