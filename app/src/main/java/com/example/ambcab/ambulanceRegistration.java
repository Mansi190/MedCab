package com.example.ambcab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ambulanceRegistration extends AppCompatActivity {

    EditText regno;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_registration);
        button=findViewById(R.id.button);
        regno=findViewById(R.id.editText);
        button.setOnClickListener(buttonOnClickListner);
    }

    private View.OnClickListener buttonOnClickListner = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            if(!validRegNo())
            {
                return;
            }
            else
            {
                isUser();

            }


        }
    };




    private void isUser()
    {
        String regNO = regno.getText().toString().trim();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("ambulanceId" );

        Query checkUser=reference.orderByChild("Id").equalTo(regNO);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    regno.setError(null);
                Toast.makeText(ambulanceRegistration.this, "Ambulance is present in database",
                        Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Maps_activity.class);
                    startActivity(intent);
                }
                else
                    {
                    regno.setError("No user exists");
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
        {

        }
        });
    }
    private Boolean validRegNo() {
        String regNO = regno.getText().toString();
        String noWhiteSpace = " ";
        if (regNO.isEmpty())
        {
            regno.setError("Ambulance ID cannot be empty");
            return false;
        }
       /* else if (!regNO.matches(noWhiteSpace))
        {
            regno.setError("No Spaces allowed");
            return false;
        } */
       else if (regNO.length() != 5)
        {
            regno.setError("Enter Valid ID");
            return false;
        }
        else
        {
            regno.setError(null);
            return true;
        }
    }
}

