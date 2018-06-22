package com.example.xwc.notificationtest;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_notice:
                Intent intent = new Intent(this,NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this,0, intent,0);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(this,"default")
                        .setContentTitle("爱宠大机密")
                        .setContentText("流氓兔")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/luna.ogg")))
                        .setVibrate(new long[]{0,1000,1000,1000})
                        .setLights(Color.GREEN,1000,1000)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.image1)))
                        /*.setStyle(new NotificationCompat.BigTextStyle().bigText("在纽约一幢热闹的公寓大楼里，有一群宠物，" +
                                "每天主人出门后、回家前这里就变成了它们的乐园——有的和其他宠物一起出去玩；有的聚在一起交流主人的糗事；" +
                                "还有的在不停捯饬自己的外貌，使自己看上去更可爱以便从主人那里要来更多的零食。" +
                                "总之，宠物们每天的“朝九晚五”是他们一天中最自由、最惬意的时光。" +
                                "在这群宠物中，有一只梗犬是当仁不让的领袖，他叫麦克，机智可爱，" +
                                "自认为是女主人生活的中心——直到她从外带回家一只懒散、没有家教的大狗杜老大。" +
                                "麦克和杜老大人生观价值观都不一样，自然很难和平共处。但当它们一起流落纽约街头后，" +
                                "两人又必须抛弃分歧、共同阻止一只被主人抛弃的宠物兔小白——后者为了报复人类，" +
                                "准备组织一支遭弃宠物大军在晚饭前向人类发起总攻。"))*/
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                manager.notify(1,notification);
                break;
                default:
                    break;
        }
    }
}
