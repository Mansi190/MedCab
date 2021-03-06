package com.example.ambcab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PersonalDetails extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    Button Submit_button;
    EditText DOB,regName,regEmail,regContactNo;
    String date;

    private  View.OnClickListener SubmitOnClickListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            submit();
        }
    };
    private  View.OnClickListener DateOnClickListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            select_date();
        }
    };

    private void select_date() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog datePickerDialog=new DatePickerDialog(this,this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();

        }
    }

    private void submit() {

        String name= regName.getText().toString();
        String email= regEmail.getText().toString();
        String contactNo=regContactNo.getText().toString();

        Intent i = new Intent(this,MedicalDetails.class);
        i.putExtra("name",name);
        i.putExtra("email",email);
        i.putExtra("contactNo",contactNo);
        i.putExtra("DOB",date);
        startActivity(i);
        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_delails);
        Submit_button=findViewById(R.id.button);
        DOB=findViewById(R.id.editText4);
        regName=(EditText) findViewById(R.id.editText);
        regEmail=(EditText) findViewById(R.id.editText2);
        regContactNo=(EditText) findViewById(R.id.editText3);
        DOB.setOnClickListener(DateOnClickListner);
        Submit_button.setOnClickListener(SubmitOnClickListner);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DOB.setShowSoftInputOnFocus(false);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date=dayOfMonth+"/"+month+"/"+year;
        DOB.setText(date);

    }
}
