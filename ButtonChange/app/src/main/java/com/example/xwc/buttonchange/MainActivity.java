package com.example.xwc.buttonchange;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
/*    @Override
    要改变按钮背景必须用onClient不能用onTouch
    public boolean onTouch(View v, MotionEvent e) {
        Vibrator vb = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            vb.vibrate(1000);
        }else if(e.getAction() == MotionEvent.ACTION_UP){
            vb.cancel();
        }
        return true;
    }*/
}
