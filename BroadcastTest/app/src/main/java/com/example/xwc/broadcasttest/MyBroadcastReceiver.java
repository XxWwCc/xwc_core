package com.example.xwc.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*自定义广播接收器 接收值为 com.example.xwc.broadcasttest.MY_BROADCAST 的广播
* 静态的广播接收器
* 需要在配置中注册：
* <receiver android:name=".MyBroadcastReceiver"
            android:enabled="true"  --是否启用该广播接收器
            android:exported="true">    --是否允许接收本程序以外的广播
            <intent-filter>
                <action android:name="com.example.xwc.broadcasttest.MY_BROADCAST"/>
            </intent-filter>
        </receiver>
* */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        abortBroadcast();//广播截断
    }
}
