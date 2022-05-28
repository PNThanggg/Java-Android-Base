package com.example.hit.pnt.service;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    /*
1. Khái niệmService là một thành phần ứng dụng (application component) có thể thực hiện các hoạt động lâu dài trong background.
Nó không cung cấp giao diện cho người dùng. Sau khi bắt đầu chạy, một Service có thể tiếp tục chạy trong một thời gian,
ngay cả khi người dùng chuyển sang ứng dụng khác. Ngoài ra một thành phần có thể liên kết (bind) với một Service để tương tác với Service đó,
thậm chí là thực hiện giao tiếp giữa các quá trình IPC (interprocess communication).
Ví dụ: một Service có thể thực hiện các giao dịch mạng, chơi nhạc, ra vào file I/O hoặc tương tác với một content provider, tất cả đều từ background

2. Phân loại Service
2.1 Foreground Service (Unbound Service)
- Một Foreground Service thực hiện một số hoạt động mà người dùng dễ nhận thấy. Ví dụ một ứng dụng nghe nhạc có thể chơi một bản nhạc và điều khiển (control)
 nó bằng Foreground Service.

- Một điều bắt buộc là Foreground Service phải hiện thị một Notification.
Foreground Service sẽ tiếp tục chạy ngay cả khi người dùng không tương tác với ứng dụng.

- Khi bạn sử dụng foreground service, bạn phải hiển thị thông báo (notification) để người dùng
chủ động biết rằng service đang chạy. Không thể loại bỏ thông báo này trừ khi service bị dừng (stopped) hoặc bị xóa khỏi foreground

2.2 Background Service (Unbound Service)
- Một Background Service sẽ thực hiện các hoạt động mà người dùng không trực tiếp nhìn thấy. Ví dụ một ứng dụng sử
dụng một service để thu gom bộ nhớ chẳng hạn, thì đó là một Background Service.

2.3 Bound Service
- Một Service được gọi là Bound Service khi một thành phần của ứng dụng ràng buộc với nó bởi lời gọi bindService().

- Một Bound Service cung cấp một giao diện Client - Server cho phép các thành phần tương tác với nó: gửi yêu cầu, nhận kết quả và thậm chí là IPC.

- Một Bound Service chỉ chạy miễn là có một thành phần ràng buộc với nó. Có thể có nhiều thành phần ràng buộc với Bound Service cùng lúc,
 nhưng khi tất cả tháo bỏ ràng buộc (unbound) thì Service sẽ bị hủy (destroyed)

3. Độ ưu tiên các loại Service
Hệ thống Android bắt buộc phải dừng một Service khi bộ nhớ ít và phải khôi phục tài nguyên hệ thống cho Activity đang được sử dụng.
Nếu Service được ràng buộc với một Activity đang sử dụng, nó ít khả năng bị giết; nếu Service được khai báo và chạy ở chế độ Foreground nó cũng khó biết giết.
 Nếu Service là Started và chạy lâu dài, hệ thống sẽ làm giảm vị trí ưu tiên của nó. Như vậy các loại service sẽ được xếp theo độ ưu tiên sau:
 Bound Service khó bị kill nhất, tiếp theo là Foreground Service và Background Service.
1 - Bound
2 - Foreground
3 - Background

4. Các giá trị trả về trong onStartCommand()
Tất cả chúng ta đều biết rằng: khi các Service tiếp tục chạy trong  background thì chắc chắn chúng sẽ tiêu tốn một số bộ nhớ để thực thi.

Vì vậy, khi có nhiều ứng dụng chạy trên thiết bị Android, bộ nhớ thiết bị sẽ dần dần bị chiếm hết , và theo thời gian khi bộ nhớ thiết bị
thấp đến mức nghiêm trọng, thì hệ thống Android bắt đầu kết thúc các quá trình (processes), để giải phóng bộ nhớ bị chiếm bởi các quá trình đó.

Nhưng bạn có thể đang thực hiện một số nhiệm vụ quan trọng với các Service, điều đó cũng có thể bị chấm dứt khi Service ngừng hoạt động.
 vì vậy những khái niệm này là để cho hệ thống Android biết bạn muốn thực hiện hành động nào khi bộ nhớ thiết bị ổn định trở lại và khi nó sẵn sàng khởi chạy lại các dịch vụ.

4.1 START_NOT_STICKY.
Yêu cầu hệ thống không cần khởi động lại dịch vụ, ngay cả khi có đủ bộ nhớ.

4.2 START_STICKY.
Yêu cầu hệ thống tạo một bản sao mới của Service, khi có đủ bộ nhớ. Ở đây bạn sẽ mất kết quả mà có thể đã tính toán trước đó

4.3 START_REDELIVER_INTENT
Yêu cầu hệ thống khởi động lại dịch vụ sau sự cố và cũng phân phối lại các Intents có tại thời điểm xảy ra sự cố.

4.4 START_STICKY_COMPATIBILITY
Giá trị này cũng giống như START_STICKY nhưng nó không chắc chắn, đảm bảo khởi động lại service.
Để tạo một Service, bạn phải tạo một lớp con (subclass) của Service hoặc sử dụng một trong các lớp con hiện có của nó. Trong quá trình triển khai,
bạn phải ghi đè (override) một số phương thức để xử lý các khía cạnh chính của vòng đời Service và cung cấp cơ chế cho phép các thành phần liên kết với Service,
nếu thích hợp. Đây là những phương thức gọi lại quan trọng nhất mà bạn nên ghi đè:

Nếu một thành phần của ứng dụng khởi động Service bằng cách gọi startService() (dẫn đến một cuộc gọi đến onStartCommand()), thì Service sẽ
tiếp tục chạy cho đến khi nó tự dừng với stopSelf() hoặc một thành phần khác dừng nó bằng cách gọi stopService().

Nếu một thành phần gọi bindService () để tạo Service và onStartCommand() không được gọi, Service chỉ chạy miễn là thành phần được liên kết
(bound) với nó. Sau khi Service không bị ràng buộc (unbound) khỏi tất cả các clients của nó, hệ thống sẽ hủy nó.
    */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}