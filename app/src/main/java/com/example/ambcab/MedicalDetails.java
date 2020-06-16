package com.example.ambcab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MedicalDetails extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseUser firebaseUser;

    String weight,age,emergencyNO;
    Dialog blood_group_selector;
    String bloodgroup,userUid;
    Button blood_group_Button,Submit_button;
    EditText regWeight,regAge,regEmergencyNO;
    private  View.OnClickListener SubmitOnClickListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            submit();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_details);
        blood_group_Button=(Button)findViewById(R.id.blood_button);
        Submit_button=findViewById(R.id.button);
        blood_group_selector=new Dialog(this);
        Submit_button.setOnClickListener(SubmitOnClickListner);
        regWeight=findViewById(R.id.editText);
        regAge=findViewById(R.id.editText1);
        regEmergencyNO=findViewById(R.id.editText2);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        userUid=firebaseUser.getUid();




    }
    public  void ShowPopup(View view)
    {
        blood_group_selector.setContentView(R.layout.blood_group_selection);
        blood_group_selector.show();
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton12:
                if (checked) {

                    bloodgroup = "A+";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton13:
                if (checked) {

                    bloodgroup = "B+";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton14:
                if (checked) {

                    bloodgroup = "AB+";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton15:
                if (checked) {

                    bloodgroup = "O+";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }

            case R.id.radioButton20:
                if (checked) {

                    bloodgroup = "A-";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton21:
                if (checked) {

                    bloodgroup = "B-";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton22:
                if (checked) {

                    bloodgroup = "AB-";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton23:
                if (checked) {

                    bloodgroup = "O-";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
        }
    }

    public void submit() {

        rootNode= FirebaseDatabase.getInstance();
        reference=rootNode.getReference("patientUsers");

        final String name=getIntent().getStringExtra("name");
        final String email=getIntent().getStringExtra("email");
        final String contactNo=getIntent().getStringExtra("contactNo");
        final String DOB=getIntent().getStringExtra("DOB");
        weight=regWeight.getText().toString();
        age=regAge.getText().toString();
        emergencyNO=regEmergencyNO.getText().toString();

        patientUserHelperClass helperClass=new patientUserHelperClass(name,email,DOB,contactNo,bloodgroup,weight,age,emergencyNO);

        reference.child(userUid).setValue(helperClass);
        Intent i = new Intent(this,BottomNavigation.class);
        startActivity(i);
    }
}
