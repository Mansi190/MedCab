package com.example.ambcab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                if(user!=null)
//                {
//                    Intent intent=new Intent(MainActivity.this,patient_Homepage.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                }
               // else {
                    Intent homeIntent = new Intent(MainActivity.this, starting.class);
                    startActivity(homeIntent);
                    finish();
              //  }
            }
        },SPLASHTIME);
    }
}
