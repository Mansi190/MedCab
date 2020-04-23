package com.example.ambcab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MedicalDetails extends AppCompatActivity {
    Dialog blood_group_selector;
    String bloodgroup;
    Button blood_group_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_details);
        blood_group_Button=(Button)findViewById(R.id.blood_button);

        blood_group_selector=new Dialog(this);



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
                    // Pirates are the best
                    bloodgroup = "A+";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton13:
                if (checked) {
                    // Ninjas rule
                    bloodgroup = "B+";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton14:
                if (checked) {
                    // Pirates are the best
                    bloodgroup = "AB+";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton15:
                if (checked) {
                    // Ninjas rule
                    bloodgroup = "O+";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }

            case R.id.radioButton20:
                if (checked) {
                    // Pirates are the best
                    bloodgroup = "A-";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton21:
                if (checked) {
                    // Ninjas rule
                    bloodgroup = "B-";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton22:
                if (checked) {
                    // Pirates are the best
                    bloodgroup = "AB-";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
            case R.id.radioButton23:
                if (checked) {
                    // Ninjas rule
                    bloodgroup = "O-";
                    blood_group_Button.setText(bloodgroup);
                    blood_group_selector.dismiss();
                    break;
                }
        }
    }

    public void submit(View view) {
        Intent i = new Intent(this,patient_Homepage.class);
        startActivity(i);
    }
}
