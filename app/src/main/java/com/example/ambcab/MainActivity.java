package com.example.ambcab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASHTIME=2000;
    AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);
        ImageView load =(ImageView)findViewById(R.id.imageView11);
        load.setBackgroundResource(R.drawable.loading);
        animation=(AnimationDrawable)load.getBackground();
        animation.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animation.stop();
                Intent homeIntent = new Intent(MainActivity.this,starting.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASHTIME);
    }
}
