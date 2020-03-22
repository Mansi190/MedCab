package com.example.ambcab;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;


public class verifyotp extends AppCompatActivity {
    EditText digit1, digit2, digit3, digit4, digit5, digit6;
    TextView timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);
        digit1=(EditText)findViewById(R.id.digit1);
        digit2=(EditText)findViewById(R.id.digit2);
        digit3=(EditText)findViewById(R.id.digit3);
        digit4=(EditText)findViewById(R.id.digit4);
        digit5=(EditText)findViewById(R.id.digit5);
        digit6=(EditText)findViewById(R.id.digit6);
        timer=(TextView) findViewById(R.id.timer);
        digit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (digit1.length() == 1) {
                        digit2.requestFocus();
                    }
                } else if (s.length() == 0) {

                }
            }

        });
        digit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (digit2.length() == 1) {
                        digit3.requestFocus();
                    }
                } else if (s.length() == 0) {
                    if (digit2.length() == 0) {
                        digit1.requestFocus();
                    }

                }

            }
        });
        digit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (digit3.length() == 1) {
                        digit4.requestFocus();
                    }
                } else if (s.length() == 0) {
                    if (digit3.length() == 0) {
                        digit2.requestFocus();
                    }

                }

            }
        });
        digit4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (digit4.length() == 1) {
                        digit5.requestFocus();
                    }
                } else if (s.length() == 0) {
                    if (digit4.length() == 0) {
                        digit3.requestFocus();
                    }

                }

            }
        });
        digit5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (digit5.length() == 1) {
                        digit6.requestFocus();
                    }
                } else if (s.length() == 0) {
                    if (digit5.length() == 0) {
                        digit4.requestFocus();
                    }

                }

            }
        });
        digit6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (digit6.length() == 1) {

                    }
                } else if (s.length() == 0) {
                    if (digit6.length() == 0) {
                        digit5.requestFocus();
                    }

                }

            }
        });
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("0:"+millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
            }
        }.start();

    }
}
