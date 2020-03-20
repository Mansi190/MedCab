package com.example.ambcab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class register extends AppCompatActivity {
    Button submit;
    EditText no;
    ImageView tick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        submit=(Button)findViewById(R.id.button);
        no=(EditText)findViewById(R.id.phoneno);
        tick=(ImageView)findViewById(R.id.tick) ;
        no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(no.length()==10){
                    tick.setVisibility(View.VISIBLE);
                }
                else {
                    tick.setImageResource(R.drawable.wrong);
                }

            }
        });
        submit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        submit.setBackground(getResources().getDrawable(R.drawable.button2));
                        submit.setText("get code");
                        submit.setTextColor(getResources().getColor(R.color.red));
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        submit.setBackground(getResources().getDrawable(R.drawable.button1));
                        submit.setText("get code");
                        submit.setTextColor(getResources().getColor(R.color.white));
                }
                return false;
            }
        });
    }
}
