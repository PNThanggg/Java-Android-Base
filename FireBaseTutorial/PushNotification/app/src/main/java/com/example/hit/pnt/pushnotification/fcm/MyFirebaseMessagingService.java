package com.example.hit.pnt.pushnotification.fcm;

import static android.os.Build.VERSION_CODES.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.hit.pnt.pushnotification.MainActivity;
import com.example.hit.pnt.pushnotification.MyApplication;
import com.example.hit.pnt.pushnotification.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = MyFirebaseMessagingService.class.getName();

    // đăng ký service trong manifests
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

//        RemoteMessage.Notification notification = message.getNotification();
//
//        if(notification == null) {
//            return;
//        }
//
//        String strTitle = notification.getTitle();
//        String strBody = notification.getBody();
//
//        sendNotification(strTitle, strBody);

        // data message
        Map<String, String> stringMap = message.getData();
        if(stringMap == null) {
            return;
        }

        // user_name và description là 2 trường giá trị của data từ fire base gửi về
        String title = stringMap.get("user_name");
        String body = stringMap.get("description");

        sendNotification(title, body);
    }

    private void sendNotification(String strTitle, String strBody) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle(strTitle)
                .setContentText(strBody)
                .setContentIntent(pendingIntent);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(manager != null) {
            manager.notify(1, notification);
        }
    }

    // token: dùng để định danh với server
    // cài app lần đầu
    // khi token thay đổi :
    //      đc phục hồi trên 1 thiêt bị mới
    //      gỡ và cài lại app
    //      clear app data
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.e(TAG, token);
    }
}
