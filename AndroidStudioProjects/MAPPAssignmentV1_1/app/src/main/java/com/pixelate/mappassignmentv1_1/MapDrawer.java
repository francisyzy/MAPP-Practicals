package com.pixelate.mappassignmentv1_1;

import android.*;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caeden on 1/8/17.
 */

public class MapDrawer extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private static final String TAG = MapDrawer.class.getSimpleName();
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawermap);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Post");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (auth.getCurrentUser() == null) {
            // user auth state is changed - user is null
            // launch login activity
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        }else
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);



        navigationView = (NavigationView) findViewById(R.id.menu_drawer);

        /*authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

                }
            }
        };*/


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                switch (id){
                    case R.id.nav_friends:
                        Toast.makeText(MapDrawer.this, "Function under constructing.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_activity:

                        break;
                    case R.id.nav_profile:
                        Intent i = new Intent(MapDrawer.this, UserProfile.class);
                        startActivity(i);
                        break;
                    case R.id.nav_savepost:
                        Toast.makeText(MapDrawer.this, "Function under constructing.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_chatroom:
                        if((FirebaseAuth.getInstance().getCurrentUser().getDisplayName() != null)&&(FirebaseAuth.getInstance().getCurrentUser().getDisplayName() != "")){
                            Intent u = new Intent(MapDrawer.this, ChatRoom.class);
                            startActivity(u);
                            break;
                        }
                        else{
                            Intent u = new Intent(MapDrawer.this, SetDisplayName.class);
                            startActivity(u);
                            break;
                        }
                    case R.id.nav_setting:
                        Intent x = new Intent(MapDrawer.this, Profile.class);
                        startActivity(x);
                        break;
                    case R.id.nav_logout:
                        auth.signOut();
                        Intent y = new Intent(MapDrawer.this, Login.class);
                        startActivity(y);
                        break;
                }
                /*FragmentManager fragmentManager = getFragmentManager();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);*/
                return true;
            }
        });


        //get current user
        //final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Intent x = new Intent(MapDrawer.this, ReadPost.class);
        Post p = (Post) marker.getTag();
        x.putExtra("Post", p );
        startActivity(x);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    /*@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/



    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.i("MDA", "onMapReady called");

        mMap = googleMap;
        CameraPosition cmapo = mMap.getCameraPosition();
        LatLng cmalo = cmapo.target;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Post");
        final List<Post> values = new ArrayList<>();

        Log.i("MDA", "Adding valueEventListener");
       myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //// TODO: 3/8/17 keith!!!!!!
                Log.i(TAG,"cakked");

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Post c = snapshot.getValue(Post.class);
                    values.add(c);
                    System.out.println("aaa");
                }
                for (Post p : values){
                    Log.i(TAG,p.toString());
                    LatLng postlocation = new LatLng(p.latitude,p.longitude);
                    Marker amarker = mMap.addMarker(new MarkerOptions().position(postlocation).title("Hello World"));
                    amarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.messageicon));
                    amarker.setTag(p);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        Log.i("MDA", "Added valueEventListener");


        while(true) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                break;

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                                    android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}
                            , 10);
                }
            }
        }

        // Add a marker in Sydney and move the camera
        try {
            Location m = mMap.getMyLocation();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(m.getLatitude(), m.getLongitude())));
        }catch (Exception e){
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(1.3082507,103.77751)));
        }
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        //mMap.getUiSettings().setScrollGesturesEnabled(false);
        //mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setPadding(0,1000,0,0);



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.map);
        View mapView = mapFragment.getView();
        if (mapView != null &&
                mapView.findViewById(new Integer(1)) != null) {
            // Get the button view
            View locationButton = ((View) mapView.findViewById(new Integer(1)).getParent()).findViewById(new Integer(2));
            // and next place it, on bottom right (as Google Maps app)
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    locationButton.getLayoutParams();
            // position on right bottom
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.setMargins(0, 0, 30, 30);
        }

        mMap.setOnMarkerClickListener(this);




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSION_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);

                    }
                }else {
                    Toast.makeText(getApplicationContext(),"This app requires location permission",Toast.LENGTH_LONG).show();
                    finish();
                }
        }
    }

    @Override
    public  void onResume(){
        super.onResume();

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // user auth state is changed - user is null
            // launch login activity
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        }else
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.search:
                Toast.makeText(MapDrawer.this, "Function under constructing.",
                        Toast.LENGTH_SHORT).show();
                    break;

            case R.id.menu:

                if (FirebaseAuth.getInstance().getCurrentUser() == null) {

                    startActivity(new Intent(MapDrawer.this, Login.class));
                    finish();
                }else{
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }


                break;

            case R.id.writepost:



                try {
                    Location myLocation = mMap.getMyLocation();  //Nullpointer exception.........
                    LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());


                    Bundle args = new Bundle();
                    args.putParcelable("currentlocation", myLatLng);


                    Intent p = new Intent(this, WritePost.class);
                    p.putExtra("bundle", args);
                    startActivity(p);
                }catch (Exception E){
                    Toast.makeText(MapDrawer.this, "Please enable location service or send location if you use an emulator.",
                            Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
