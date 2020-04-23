package com.example.ambcab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class patient_Homepage extends AppCompatActivity {
    ProgressBar select;
    ImageButton emergency;
    private Handler mHandler = new Handler();
    public int mValue=0;

    private Runnable incrementRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(incrementRunnable); // remove our old runnable, though I'm not really sure if this is necessary
            if(emergency.isPressed()) { // check if the button is still in its pressed state
                  if(mValue<100) {
                      mValue++;
                      select.setProgress(mValue);
                  }
                mHandler.postDelayed(incrementRunnable, 25); // call for a delayed re-check of the button's state through our handler. The delay of 100ms can be changed as needed.
            }
            else {
                if(mValue>0) {
                    mValue-=5;
                    select.setProgress(mValue);
                }
                mHandler.postDelayed(incrementRunnable, 25);

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__homepage);
        select = (ProgressBar) findViewById(R.id.progressBar);
        emergency = (ImageButton) findViewById(R.id.EMERGENCY_button);
        select.setProgress(mValue);
        emergency.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mHandler.postDelayed(incrementRunnable, 0); // initial call for our handler.
                return true;
            }
        });

    }

}
