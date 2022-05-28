package com.example.hit.pnt.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {

    /* 2. onBind().
    Hệ thống sẽ gọi phương thức này khi một thành phần khác gọi đến Service bằng câu lệnh bindService(). Khi bạn triển khai phương thức này bạn
    phải cung cấp một giao diện để client có thể giao tiếp với Service thông qua một đối tượng IBinder do Service trả về. Khi bạn kế thừa từ lớp
    Service của Android bạn phải luôn luôn override phương thức này, nhưng nếu bạn không muốn ràng buộc (bind) với Service bạn có thể return null.
     */

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* 1. onStartCommand().
    Hệ thống gọi phương thức này khi một thành phần khác (Activity chẳng hạn) gọi đến Service bằng câu lệnh startService(). Khi phương thức này được thực hiện,
    Service được khởi động và có thể chạy trong background vô thời hạn. Khi công việc hoàn thành bạn nên stop bằng cách gọi stopService() từ một thành phần khác,
    hoặc cho chính Service gọi stopSelf(). Nếu bạn chỉ muốn ràng buộc (bind) với Service thì không nên sử dụng onStartCommand().
    */

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /*
    3. onCreate().
    Hệ thống gọi phương thức này khi Service được khởi tạo, và nó chỉ chạy một lần trước khi onStartCommand() hoặc onBind() được gọi. Nếu Service đã
    chạy thì phương thức này không được gọi lại lần nào nữa.
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /* 4. onDestroy().
    Hệ thống gọi phương thức này khi Service không được sử dụng nữa và đang bị hủy (destroy). Bạn cũng nên giải phóng tài nguyên như các Threads,
    Listeners hay Receivers ở đây. Đây là phương thức cuối cùng được gọi của Service.
     */

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
