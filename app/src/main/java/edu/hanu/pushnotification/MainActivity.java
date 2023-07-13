package edu.hanu.pushnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.security.PrivateKey;
import java.security.spec.DSAPrivateKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TITLE_PUSH_NOTIFICATION = "Cựu thứ trưởng Ngoại giao Tô Anh Dũng khai nhận tiền 'vì nể nang'";
    private static final String CONTENT_PUSH_NOTIFICATION = "Ông Tô Anh Dũng thừa nhận 37 lần nhận tổng 21,5 tỷ đồng hối lộ trong 327 \"chuyến bay giải cứu\", song khẳng định không đòi hỏi, \"nhận tiền vì nể nang\".";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoToListProduct = findViewById(R.id.btn_go_to_list_product);

        Button btnSendNotification = findViewById(R.id.btn_send_notification);
        btnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

        Button btnSendNotification_2 = findViewById(R.id.btn_send_notification_2);
        btnSendNotification_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotificationChannel2();
            }
        });

        Button btnCustomNotification = findViewById(R.id.btn_custom_notification);
        btnCustomNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCustomNotification();
            }
        });

        btnGoToListProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListProductActivity.class);
                startActivity(intent);
            }
        });
    }


    private void sendNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle(TITLE_PUSH_NOTIFICATION)
                .setContentText(CONTENT_PUSH_NOTIFICATION)
                .setSmallIcon(R.drawable.bell)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(CONTENT_PUSH_NOTIFICATION))
                .setLargeIcon(bitmap)
                .setSound(uri)
                .setColor(getResources().getColor(R.color.teal_700))
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification);

    }

    private void sendNotificationChannel2() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.download);
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound_notification_custom);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setContentTitle("Title push notification channel 2")
                .setContentText("Message push notification channel 2")
                .setSmallIcon(R.drawable.bell)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon((Icon) null))
                .setLargeIcon(bitmap)
                .setSound(sound)
                .setColor(getResources().getColor(R.color.teal_700))
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification);

    }
    private int getNotificationId() {
        return (int) new Date().getTime();
    }


    private void sendCustomNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.download);
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound_notification_custom);

        // collapsed
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.layout_custom_notification);
        notificationLayout.setTextViewText(R.id.tv_title_custom_notification, "Title custom notification");
        notificationLayout.setTextViewText(R.id.tv_message_custom_notification, "Message custom notification");

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strDate = sdf.format(new Date());
        notificationLayout.setTextViewText(R.id.tv_time_custom_notification, strDate);

        // expanded
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.layout_custom_notification_expanded);
        notificationLayoutExpanded.setTextViewText(R.id.tv_title_custom_notification_expanded, "Title custom notification expanded");
        notificationLayoutExpanded.setTextViewText(R.id.tv_message_custom_notification_expanded, "Message custom notification expanded");
        notificationLayoutExpanded.setImageViewBitmap(R.id.img_custom_notification_expanded, bitmap);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setSmallIcon(R.drawable.bell)
                .setSound(sound)
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification);
    }
}