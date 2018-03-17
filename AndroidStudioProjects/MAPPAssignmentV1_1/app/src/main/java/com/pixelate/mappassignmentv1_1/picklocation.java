package com.pixelate.mappassignmentv1_1;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by caeden on 19/6/17.
 */

public class picklocation extends FragmentActivity implements OnMapReadyCallback,View.OnClickListener{

        private GoogleMap mMap;
        private static final String TAG = com.pixelate.mappassignmentv1_1.MapDrawer.class.getSimpleName();
        private final static int MY_PERMISSION_FINE_LOCATION = 101;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            /*setContentView(R.layout.activity_maps);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            // Create drop pin using custom image
            ImageView dropPinView = new ImageView(this);
            dropPinView.setImageResource(R.drawable.ic_droppin_24dp);

//          Statically Set drop pin in center of screen
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
            float density = getResources().getDisplayMetrics().density;
            params.bottomMargin = (int) (12 * density);
            dropPinView.setLayoutParams(params);
            mMap.addView(dropPinView);*/
        }


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            CameraPosition cmapo = mMap.getCameraPosition();
            LatLng cmalo = cmapo.target;


            // Add a marker in Sydney and move the camera


            mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
            //mMap.getUiSettings().setScrollGesturesEnabled(false);
            //mMap.getUiSettings().setZoomGesturesEnabled(false);
            mMap.getUiSettings().setRotateGesturesEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);




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




        }





    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.search:
                if((FirebaseAuth.getInstance().getCurrentUser().getDisplayName() != null)&&(FirebaseAuth.getInstance().getCurrentUser().getDisplayName() != "")){
                    Intent i = new Intent(this, ChatRoom.class);
                    startActivity(i);
                    break;
                }
                else{
                    Intent i = new Intent(this, SetDisplayName.class);
                    startActivity(i);
                    break;
                }
            case R.id.menu:
                Intent o = new Intent(this, Profile.class);
                startActivity(o);
                break;
        }
    }
}

