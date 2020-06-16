package com.example.ambcab.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ambcab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    TextView bloodgrp,name,phoneno,email;
    private DatabaseReference mDatabase;
    String userUid;
    FirebaseUser firebaseUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        bloodgrp=(TextView)root.findViewById(R.id.bloodgrp);
        name=(TextView)root.findViewById(R.id.Name);
        phoneno=(TextView)root.findViewById(R.id.phoneno);
        email=(TextView)root.findViewById(R.id.email);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("patientUsers");
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        userUid=firebaseUser.getUid();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String val=dataSnapshot.child(userUid).child("name").getValue(String.class);
                String val1=dataSnapshot.child(userUid).child("email").getValue(String.class);
                String val2=dataSnapshot.child(userUid).child("contactNO").getValue(String.class);
                name.setText(val);
                email.setText(val1);
                phoneno.setText(val2);
                Log.i("val+val1+val2",val+val1+val2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }
}
