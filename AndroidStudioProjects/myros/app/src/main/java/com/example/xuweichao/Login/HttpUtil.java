package com.example.xuweichao.Login;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    public static void sendHttpRequest(final String json,final String username ,final String password,final HttpCallbackListener listener,final String ...maps) {
        Log.i("tag","调用httputil的方法");
        //Log.i("tag",maps[4]);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                try {
                    HttpURLConnection connection = null;
                    String address = "http://192.168.1.114:8080/robot_server/user/"+json;
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    if(json.equals("checkLogin.json")) {
                        out.write(("rname="+username+"&password="+password).getBytes());
                    }else {
                        out.write(("rname="+username+"&password="+password+"&sex="+maps[0]+"&address="+maps[1]+"&email="+maps[2]+"&tel="+maps[3]+"&QQ="+maps[4]).getBytes());
                    }
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
					/*connection.setDoInput(true);
					connection.setDoOutput(true);*/
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null) {
                        response.append(line);
                    }if(listener != null) {
                        listener.onFinish(response.toString());
                    }
                }  catch (IOException e) {
                    // TODO Auto-generated catch block
                    if(listener != null) {
                        listener.onError(e);
                    }
                }
            }}).start();
    }
    public interface HttpCallbackListener{
        void onFinish(String response);
        void onError(Exception e);
    }
}
