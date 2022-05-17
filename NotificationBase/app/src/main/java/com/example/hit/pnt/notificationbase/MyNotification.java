package com.example.hit.pnt.notificationbase;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyNotification extends Application {
    public static final String CHANNEL_ID_1 = "CHANNEL_1";
    public static final String CHANNEL_ID_2 = "CHANNEL_2";
    // tạo xong class này thì cần vào manifests -> application -> add: android:name= ".MyNotification"
    @Override
    public void onCreate() {
        super.onCreate();
        creteNotificationChannel();
    }

    private void creteNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // config channel 1
            CharSequence name = getString(R.string.channel_name);
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_1, name, importance);
            channel.setDescription(description);

            // config channel 2
            CharSequence name2 = getString(R.string.channel_name_2);
            String description2 = getString(R.string.channel_description_2);
            int importance2 = NotificationManager.IMPORTANCE_DEFAULT; // mức độ ưu tiên
            NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID_2, name2, importance2);
            channel.setDescription(description2);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
                notificationManager.createNotificationChannel(channel2);
            }
        }
    }
}
