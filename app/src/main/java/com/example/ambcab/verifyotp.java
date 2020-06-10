package com.example.ambcab;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzl;

import java.util.concurrent.TimeUnit;


public class verifyotp extends AppCompatActivity {
    EditText digit1, digit2, digit3, digit4, digit5, digit6;
    ImageView tick;
    TextView timer;
    Button verify_button;
    String OTP_received;
    FirebaseAuth mAuth;
    static String phoneNO;
    String verificationCodeSentBySystem;
    private  View.OnClickListener verifyOnClickListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            verifyotp();
        }
    };
    private void verifyotp(){
        if(digit1.length()!=0 && digit2.length()!=0 && digit3.length()!=0 && digit4.length()!=0 && digit5.length()!=0 && digit6.length()!=0){
            OTP_received=digit1.getText().toString();
            OTP_received+=digit2.getText().toString();
            OTP_received+=digit3.getText().toString();
            OTP_received+=digit4.getText().toString();
            OTP_received+=digit5.getText().toString();
            OTP_received+=digit6.getText().toString();

            verifycode(OTP_received);



        }
    }


    private void verifycode(String codeByUser)
    {
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationCodeSentBySystem,codeByUser);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(verifyotp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                    if(task.isSuccessful())
                    {
                        tick.setVisibility(View.VISIBLE);
                        tick.setImageResource(R.drawable.tick);
                        Intent intent=new Intent(getApplicationContext(),PersonalDetails.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }
                    else{
                        Log.i("TAG","create user failed");
                        tick.setVisibility(View.VISIBLE);
                        tick.setImageResource(R.drawable.wrong);
                        Toast.makeText(verifyotp.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

            }
        });
                //.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  //  @Override
                    //public void onComplete(@NonNull Task<AuthResult> task) {
                      //  if(task.isSuccessful())
                        //{
                         //   Log.i("TAG", "OTP recieved");
                           // Intent i = new Intent(verifyotp.this,Select_user.class);
                            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           // startActivity(i);
                            //overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                        //}
                        //else {
                          //  Log.i("TAG","create user failed");
                            //Toast.makeText(verifyotp.this, "Authentication failed.",
                              //      Toast.LENGTH_SHORT).show();

                        //}
                    //}
                //});
    }
    private void sendVerificationCodeToUser(String phoneNO)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNO,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
    {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationCodeSentBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
        {
            String OTP_received = phoneAuthCredential.getSmsCode();
            if (OTP_received != null) {
                verifycode(OTP_received);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Log.i("TAG", "verification of user failed" );
            Toast.makeText(verifyotp.this, "verification of user failed",
                    Toast.LENGTH_SHORT).show();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);

        mAuth=FirebaseAuth.getInstance();
        phoneNO=getIntent().getStringExtra("phoneNO");
        sendVerificationCodeToUser( phoneNO);


        digit1=(EditText)findViewById(R.id.digit1);
        digit2=(EditText)findViewById(R.id.digit2);
        digit3=(EditText)findViewById(R.id.digit3);
        digit4=(EditText)findViewById(R.id.digit4);
        digit5=(EditText)findViewById(R.id.digit5);
        digit6=(EditText)findViewById(R.id.digit6);
        timer=(TextView) findViewById(R.id.timer);
        verify_button=(Button)findViewById(R.id.verify);
        verify_button.setOnClickListener(verifyOnClickListner);
        tick=(ImageView)findViewById(R.id.imageView8);



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
                //sendVerificationCodeToUser(phoneNO);     //function to resend OTP to user
                timer.setText(Html.fromHtml("<p><u>Click here</u></p>"));
                timer.setTextColor(Color.BLUE);
                timer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resendotp();
                    }


                });
            }
        }.start();

    }
    private void resendotp() {
        sendVerificationCodeToUser(phoneNO);
        timer.setTextColor(Color.RED);
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("0:"+millisUntilFinished / 1000);
            }

            public void onFinish() {
                //sendVerificationCodeToUser(phoneNO);     //function to resend OTP to user
                timer.setText(Html.fromHtml("<p><u>Click here</u></p>"));
                timer.setTextColor(Color.BLUE);
                timer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resendotp();
                    }


                });
            }
        }.start();

    }
}
