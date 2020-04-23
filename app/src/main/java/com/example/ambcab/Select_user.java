package com.example.ambcab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Select_user extends AppCompatActivity {
    Button Patient_button;
    private  View.OnClickListener PatientOnClickListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Patient();
        }
    };

    private void Patient() {
        Intent i = new Intent(this,PersonalDetails.class);
        startActivity(i);
        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        Patient_button=findViewById(R.id.button2);
        Patient_button.setOnClickListener(PatientOnClickListner);
    }
}
