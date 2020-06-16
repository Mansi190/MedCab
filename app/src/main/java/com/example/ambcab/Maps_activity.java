package com.example.ambcab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;


public class Maps_activity extends AppCompatActivity implements OnMapReadyCallback , GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener {
    ProgressBar progressBar;
    TextView textView;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DataSnapshot dataSnapshot;
    GoogleMap nMap;



    private  View.OnClickListener startOnClickListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startShift();
        }
    };


    private void startShift() {

        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView.setText("Searching for patient");
    }

    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activity);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        textView = (TextView) findViewById(R.id.textView16);
        textView.setOnClickListener(startOnClickListner);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
        }
        Task<Location> task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null){
                    SupportMapFragment supportMapFragment= (SupportMapFragment)
                            getSupportFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(Maps_activity.this);
                }
            }

        });
    }







    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){

            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {

                }
                break;
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        nMap=googleMap;
        buildGoogleApiClient();
        nMap.setMyLocationEnabled(true);
//        LatLng latLng=new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
//        MarkerOptions markerOptions= new MarkerOptions().position(latLng).title("You are here").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_navigation_blue_24dp));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
//        googleMap.addMarker(markerOptions);

    }

    protected synchronized void buildGoogleApiClient()
    {
        mGoogleApiClient=new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build(); {
            mGoogleApiClient.connect();
        }
    }


    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        Drawable vectorDrawable= ContextCompat.getDrawable(context,vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap=Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest=new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation=location;
        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
        //MarkerOptions markerOptions= new MarkerOptions().position(latLng).icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_navigation_blue_24dp));
        nMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        nMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //nMap.addMarker(markerOptions);
        String regNO=getIntent().getStringExtra("regNO");
        String ambId= regNO;

        databaseReference=FirebaseDatabase.getInstance().getReference("ambAvaliable");
        databaseReference.child(ambId).setValue(regNO);
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("ambAvaliable");

        GeoFire geoFire=new GeoFire(ref);
        geoFire.setLocation(ambId,new GeoLocation(mLastLocation.getLatitude(),mLastLocation.getLongitude()));



    }

    @Override
    protected void onStop() {
        super.onStop();
        String regNO=getIntent().getStringExtra("regNO");
        String ambId= regNO;
        databaseReference=FirebaseDatabase.getInstance().getReference("ambAvaliable");
        databaseReference.child(ambId).setValue(regNO);

        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ambAvailable");

        GeoFire geoFire = new GeoFire(ref);
        geoFire.removeLocation(ambId);


//       DatabaseReference ref=FirebaseDatabase.getInstance().getReference("ambAvaliable");
//
//        GeoFire geoFire=new GeoFire(ref);
//        geoFire.removeLocation(ambId);

    }
}
