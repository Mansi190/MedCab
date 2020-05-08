package com.example.ambcab.ui.home;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ambcab.R;

public class HomeFragment extends Fragment {
    ProgressBar select;
    ImageButton emergency;
    ImageView particle,particle1,imageView;
    TextView safe,injured,alert,serious,text1,text2,text3,text4,text5;
    int option=0;
    private Handler mHandler = new Handler();
    public int mValue=0;

    private Runnable incrementRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(incrementRunnable); // remove our old runnable, though I'm not really sure if this is necessary
            if(emergency.isPressed()) { // check if the button is still in its pressed state
                text1.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.INVISIBLE);
                text4.setVisibility(View.INVISIBLE);
                text5.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                if(mValue<100) {
                    mValue++;
                    select.setProgress(mValue);
                    if(select.getProgress()>=32)
                    {
                        select.setBackground((Drawable)getResources().getDrawable(R.drawable.bacground_progress1));
                        option=1;
                        safe.setVisibility(View.INVISIBLE);
                        injured.setVisibility(View.VISIBLE);
                        alert.setVisibility(View.INVISIBLE);
                        serious.setVisibility(View.INVISIBLE);
                    }
                    if(select.getProgress()>=49)
                    {
                        select.setBackground((Drawable)getResources().getDrawable(R.drawable.bacground_progress2));
                        option=2;
                        safe.setVisibility(View.INVISIBLE);
                        injured.setVisibility(View.INVISIBLE);
                        alert.setVisibility(View.VISIBLE);
                        serious.setVisibility(View.INVISIBLE);
                    }
                    if(select.getProgress()>=80)
                    {
                        select.setBackground((Drawable)getResources().getDrawable(R.drawable.bacground_progress3));
                        option=3;
                        safe.setVisibility(View.INVISIBLE);
                        injured.setVisibility(View.INVISIBLE);
                        alert.setVisibility(View.INVISIBLE);
                        serious.setVisibility(View.VISIBLE);
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
                            safe.setVisibility(View.VISIBLE);
                            injured.setVisibility(View.INVISIBLE);
                            alert.setVisibility(View.INVISIBLE);
                            serious.setVisibility(View.INVISIBLE);

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



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_patient__homepage, container, false);
        select = (ProgressBar) root.findViewById(R.id.progressBar);
        emergency = (ImageButton) root.findViewById(R.id.EMERGENCY_button);
        select.setProgress(mValue);
        particle=(ImageView)root.findViewById(R.id.particle1);
        particle1=(ImageView)root.findViewById(R.id.particle2);
        imageView=(ImageView)root.findViewById(R.id.imageView9);
        safe=(TextView)root.findViewById(R.id.safe);
        injured=(TextView)root.findViewById(R.id.injured);
        alert=(TextView)root.findViewById(R.id.alert);
        serious=(TextView)root.findViewById(R.id.serious);
        text1=(TextView)root.findViewById(R.id.textView10);
        text2=(TextView)root.findViewById(R.id.textView11);
        text3=(TextView)root.findViewById(R.id.textView12);
        text4=(TextView)root.findViewById(R.id.textView13);
        text5=(TextView)root.findViewById(R.id.textView14);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float height = particle.getHeight();
                final float translationY = height * progress;
                particle.setTranslationY(translationY);
                particle1.setTranslationY(translationY - height);
            }
        });
        animator.start();
        emergency.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mHandler.postDelayed(incrementRunnable, 0); // initial call for our handler.
                return true;
            }
        });

        return root;
    }
}
