package com.example.everis.android.androidbasicoaplicado;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String GET_URL = "https://jsonplaceholder.typicode.com/posts/1";
    private static final String POST_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final String POST_BODY = "{\"title\":\"foo\",\"body\": \"bar\",\"userId\": 1}";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.text);

        findViewById(R.id.button_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Api.ApiCallGet(new Api.ApiCallListener() {
                    @Override
                    public void success(String response) {
                        textView.setText(response);
                    }
                }).execute(new String[]{GET_URL});
            }
        });

        findViewById(R.id.button_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Api.ApiCallPost(new Api.ApiCallListener() {
                    @Override
                    public void success(String response) {
                        textView.setText(response);
                    }
                }).execute(new String[]{POST_URL, POST_BODY});
            }
        });
    }
}
