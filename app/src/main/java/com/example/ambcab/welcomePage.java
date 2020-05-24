package com.example.ambcab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomePage extends AppCompatActivity {
    Button button;
    private  View.OnClickListener buttonOnClickListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            welcome();
        }
    };

    private void welcome() {
        Intent i = new Intent(this,verifyotp.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        button=findViewById(R.id.button);

        button.setOnClickListener(buttonOnClickListner);

        };




}
