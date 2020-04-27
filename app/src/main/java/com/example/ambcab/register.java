package com.example.ambcab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    Button submit;
    EditText no;
    String phoneNO;
    boolean valid=false;
    ImageView tick;
    private  View.OnClickListener registerOnClickListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            register();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        submit=(Button)findViewById(R.id.button);
        no=(EditText)findViewById(R.id.phoneno);
        tick=(ImageView)findViewById(R.id.tick) ;
        submit.setOnClickListener(registerOnClickListner);
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
                    tick.setImageResource(R.drawable.tick);
                    valid=true;
                }
                else {
                    tick.setImageResource(R.drawable.wrong);
                    valid=false;
                }

            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slideinleft,R.anim.slideoutright);
    }
    private void register(){
        Log.d("TAG",String.valueOf(valid));
        System.out.println(valid);
        if(valid){
            phoneNO=no.getText().toString();
            phoneNO="+91"+phoneNO;

            System.out.println(phoneNO);
            Log.d("TAG",phoneNO);
            Intent i = new Intent(this,verifyotp.class);
            i.putExtra("phoneNO",phoneNO);
            startActivity(i);
            overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
        }
        else {

        }

    }

}
