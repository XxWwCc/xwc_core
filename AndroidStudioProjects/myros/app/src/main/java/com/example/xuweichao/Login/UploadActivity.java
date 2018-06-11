package com.example.xuweichao.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wuliangzhu.myros.MainActivity;
import com.example.wuliangzhu.myros.R;

/**
 * Created by wlz on 18-3-28.
 */

public class UploadActivity extends Activity implements View.OnClickListener{
    private EditText username,password;
    private Button upload,register;
    private String name,pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        username =  findViewById(R.id.username);
        password = findViewById(R.id.password);
        upload = findViewById(R.id.upload);
        register =  findViewById(R.id.register);
        upload.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.register:
                Intent intent = new Intent(UploadActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.upload:
                String address = "checkLogin.json";
                name = username.getText().toString().trim();
                pass = password.getText().toString().trim();
                Log.i("TAG", name+pass);
                //Toast.makeText(UploadActivity.this, "点击了登录按钮", Toast.LENGTH_LONG).show();
                if(name.equals("") || pass.equals("")){
                    Toast.makeText(UploadActivity.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
                }else {
                    HttpUtil.sendHttpRequest(address, name, pass, new HttpUtil.HttpCallbackListener() {


                        public void onFinish(String response) {
                            // TODO Auto-generated method stub

                            Log.i("AVG", response);
                            if (response.equals("验证失败")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UploadActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                //Toast.makeText(FirstActivity.this, name, Toast.LENGTH_LONG).show();
                                //Toast.makeText(FirstActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(UploadActivity.this, MainActivity.class);
                                intent1.putExtra("data", response);
                                startActivity(intent1);
                            }
                        }

                        public void onError(Exception e) {
                            // TODO Auto-generated method stub
                        }
                    });
                }
                break;
        }
    }
}
