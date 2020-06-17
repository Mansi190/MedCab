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

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Maps_activity extends AppCompatActivity implements OnMapReadyCallback , GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener , RoutingListener {
    ProgressBar progressBar;
    TextView textView;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DataSnapshot dataSnapshot;
    GoogleMap nMap;
    String patientId=" ";



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
        getAssignedPatient();

    }

    private void getAssignedPatient() {
        String ambId=getIntent().getStringExtra("regNO");
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("assignedAmb").child(ambId);
//        ambref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
//                if (map.get("assingnedPatientId") != null) {
//                    patientId = map.get("assingnedPatientId").toString();
//                    getAssignedPatientPickupLocation();
//                }
//            }
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if( dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.containsKey("assingnedPatientId")) {
                        patientId = map.get("assingnedPatientId").toString();
                        getAssignedPatientPickupLocation();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getAssignedPatientPickupLocation() {
        DatabaseReference getAssignedPatientPickupLocation=FirebaseDatabase.getInstance().getReference().child("activePatients").child(patientId).child("l");
        getAssignedPatientPickupLocation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    List<Object> map=(List<Object>) dataSnapshot.getValue();
                    double locationLat=0;
                    double locationLng=0;
                    if(map.get(0)!=null){
                        locationLat=Double.parseDouble(map.get(0).toString());
                    }
                    if(map.get(1)!=null){
                        locationLng=Double.parseDouble(map.get(1).toString());

                    }
                    LatLng patientLatLng=new LatLng(locationLat,locationLng);
                    MarkerOptions markerOptions= new MarkerOptions().position(patientLatLng).title("Patient is here").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_place_red_24dp));
                    nMap.addMarker(markerOptions);
                    getRouteToPatient(patientLatLng);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//
//    private void getAmbulanceLocation() {
//        String ambId=getIntent().getStringExtra("regNO");
//        DatabaseReference getAmbulanceLocation=FirebaseDatabase.getInstance().getReference().child("activeAmb").child(ambId).child("l");
//        getAmbulanceLocation.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    List<Object> map=(List<Object>) dataSnapshot.getValue();
//                    double locationLat=0;
//                    double locationLng=0;
//                    if(map.get(0)!=null){
//                        locationLat=Double.parseDouble(map.get(0).toString());
//                    }
//                    if(map.get(1)!=null){
//                        locationLng=Double.parseDouble(map.get(1).toString());
//
//                    }
//                    LatLng ambulanceLatLng=new LatLng(locationLat,locationLng);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }


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
        polylines = new ArrayList<>();


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

        databaseReference=FirebaseDatabase.getInstance().getReference("activeAmb");
        databaseReference.child(ambId).setValue("true");
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("activeAmb");

        GeoFire geoFire=new GeoFire(ref);
        geoFire.setLocation(ambId,new GeoLocation(mLastLocation.getLatitude(),mLastLocation.getLongitude()));



    }

    @Override
    protected void onStop() {
        super.onStop();
          String ambId=getIntent().getStringExtra("regNO");



        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("activeAmb");

        GeoFire geoFire = new GeoFire(ref);
        geoFire.removeLocation(ambId);


//       DatabaseReference ref=FirebaseDatabase.getInstance().getReference("activeAmb");
//
//        GeoFire geoFire=new GeoFire(ref);
//        geoFire.removeLocation(ambId);

    }
    private void getRouteToPatient(LatLng patientLatLng) {
        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(this)
                .alternativeRoutes(false)
                .waypoints(new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude()), patientLatLng)
                .build();
        routing.execute();
    }


    @Override
    public void onRoutingFailure(RouteException e) {
        if(e != null) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingStart() {

    }
    private List<Polyline> polylines;
    private static final int[] COLORS = new int[]{R.color.primary_dark_material_light};
    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i <route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = nMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getApplicationContext(),"Route "+ (i+1) +": distance - "+ route.get(i).getDistanceValue()+": duration - "+ route.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingCancelled() {

    }
//    private void erasePolylines(){
//        for(Polyline line: polylines){
//            line.remove();
//        }                                         //to erase polylines after ambulance reaches patient
//        polylines.clear();
//    }
}