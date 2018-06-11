package com.example.xuweichao.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wuliangzhu.myros.R;

/**
 * Created by wlz on 18-3-28.
 */

public class RegisterActivity extends Activity{
    private Button register;
    private TextView back;
    private EditText username1,password1,repassword,phone1,QQ1,mail1,place1;
    private String rname,password,rpass,tel,qq,email,address,sex = "男";
    //private RadioButton male,female;
    private RadioGroup rg;
    private RadioButton rd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        register =  findViewById(R.id.register1);
        back =  findViewById(R.id.back1);
        rg = findViewById(R.id.sex1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rd = findViewById(checkedId);
                sex = rd.getText().toString();
            }
        });
        /*male = findViewById(R.id.male1);
        female = findViewById(R.id.female1);
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = "女";
            }
        });
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = "男";
            }
        });*/
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username1 = findViewById(R.id.username1);
                password1 = findViewById(R.id.password1);
                repassword = findViewById(R.id.repassword1);
                QQ1 = findViewById(R.id.QQ1);
                mail1 = findViewById(R.id.mail1);
                place1 = findViewById(R.id.place1);
                phone1 = findViewById(R.id.phone1);

                rname = username1.getText().toString().trim();
                password = password1.getText().toString().trim();
                rpass = repassword.getText().toString().trim();
                address = place1.getText().toString().trim();
                email = mail1.getText().toString().trim();
                tel = phone1.getText().toString().trim();
                qq = QQ1.getText().toString().trim();
                //Toast.makeText(RegisterActivity.this, rname+password+rpass+address+email+tel+qq+sex, Toast.LENGTH_LONG).show();
                if (rname.equals("") || password.equals("") || rpass.equals("") || address.equals("") || email.equals("") || tel.equals("") || qq.equals("")) {

                    Toast.makeText(RegisterActivity.this, "信息不齐全", Toast.LENGTH_LONG).show();
                    //Toast.makeText(SecondActivity.this, sex, Toast.LENGTH_LONG).show();
                } else if (!password.equals(rpass)) {
                    Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_LONG).show();
                } else {

                    HttpUtil.sendHttpRequest("addUser.json", rname, password, new HttpUtil.HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {

                            // TODO Auto-generated method stub
                            Log.i("AVG", response);
                            if (response.equals("用户名已存在")) {
                                //Output();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                Intent intent = new Intent(RegisterActivity.this, UploadActivity.class);
                                startActivity(intent);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "注册成功！请登录", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            // TODO Auto-generated method stub
                        }
                    }, sex, address, email, tel, qq);
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(RegisterActivity.this,UploadActivity.class);
                startActivity(intent1);
            }
        });

    }
}
