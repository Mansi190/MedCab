package com.example.ambcab;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
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
    int option=0;
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
                      if(select.getProgress()>=32)
                      {
                          select.setBackground(getDrawable(R.drawable.bacground_progress1));
                          option=1;
                      }
                      if(select.getProgress()>=49)
                      {
                          select.setBackground(getDrawable(R.drawable.bacground_progress2));
                          option=2;
                      }
                      if(select.getProgress()>=80)
                      {
                          select.setBackground(getDrawable(R.drawable.bacground_progress3));
                          option=3;
                      }
                  }
                mHandler.postDelayed(incrementRunnable, 25); // call for a delayed re-check of the button's state through our handler. The delay of 100ms can be changed as needed.
            }
            else {
                switch (option) {
                    case 0:{
                        if (mValue > 0) {
                            mValue -= 5;
                            select.setProgress(mValue);
                            }

                        }
                        break;
                    case 1:{
                        if (mValue > 32) {
                            mValue -= 5;
                            select.setProgress(mValue);
                        }
                        if (mValue < 32) select.setProgress(32);
                        break;
                    }
                    case 2:{
                        if (mValue > 49) {
                            mValue -= 5;
                            select.setProgress(mValue);
                        }
                        if (mValue < 49) select.setProgress(49);
                        break;
                    }
                    case 3:{
                        if (mValue > 80) {
                            mValue -= 5;
                            select.setProgress(mValue);
                        }
                        if (mValue < 80) select.setProgress(80);
                        break;
                    }
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
