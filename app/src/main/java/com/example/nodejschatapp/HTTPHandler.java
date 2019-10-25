package com.example.nodejschatapp;



import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HTTPHandler {
    private final OkHttpClient client = new OkHttpClient();
    public void makePost(final String room,final String pwr) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
               

        RequestBody requestBody = new FormBody.Builder()
                .add("roomname", room)
                .add("password", pwr)
                .build();

        Request request = new Request.Builder()
                .url("https://zerai-node-v3-chat-app.herokuapp.com/createloby")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            System.out.println(response.body().string());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    });
    }
}
