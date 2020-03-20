package com.example.ambcab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class starting extends AppCompatActivity {
    Button login,aboutus;
    private View.OnClickListener loginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        login = (Button)findViewById(R.id.login);
        aboutus=(Button)findViewById(R.id.aboutus);
        final Intent i = new Intent(getApplicationContext(),register.class);
        login.setOnClickListener(loginOnClickListener);

       /* login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        login.setBackground(getResources().getDrawable(R.drawable.button2));
                        login.setText("CREATE ACCOUNT");
                        login.setTextColor(getResources().getColor(R.color.red));
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        login.setBackground(getResources().getDrawable(R.drawable.button1));
                        login.setText("CREATE ACCOUNT");
                        login.setTextColor(getResources().getColor(R.color.white));

                        startActivity(i);
                        setContentView(R.layout.activity_register);
                        return true; // if you want to handle the touch event
                }
                return false;
            }

        });
        aboutus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        aboutus.setBackground(getResources().getDrawable(R.drawable.button1));
                        aboutus.setText("ABOUT US");
                        aboutus.setTextColor(getResources().getColor(R.color.white));
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        aboutus.setBackground(getResources().getDrawable(R.drawable.button2));
                        aboutus.setText("ABOUT US");
                        aboutus.setTextColor(getResources().getColor(R.color.red));
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });*/

    }
    private void login() {

            }
}
