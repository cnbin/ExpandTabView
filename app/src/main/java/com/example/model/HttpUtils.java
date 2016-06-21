package com.example.model;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wuyongmin on 16/6/21.
 */
public class HttpUtils {
    public static void getNewsJSON(final String url, final Handler handler){

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn;
                InputStream is;
                try {
                    conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setRequestMethod("GET");
                    is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String line = "";
                    StringBuilder result = new StringBuilder();
                    while ( (line = reader.readLine()) != null ){
                        result.append(line);

                    }
                    Message msg = new Message();
                    msg.obj = result.toString();
                    System.out.println("msg obj is %s"+msg.obj);
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
