package com.example.sharedpref;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import pl.droidsonroids.gif.GifImageView;

public class LoadingActivity extends AppCompatActivity {
    GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        gifImageView = findViewById(R.id.gifView);

        // Get username from intent
        String username = getIntent().getStringExtra("username");

        // After 3 sec go to validateActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LoadingActivity.this, validateActivity.class);
                i.putExtra("username", username);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}
