package com.example.paul.mymechanic;

import android.*;
import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.CollationElementIterator;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        if (mGoogleApiClient == null) {
            // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .addApi(AppIndex.API).build();
        }

//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this /* FragmentActivity */,
//                        this /* OnConnectionFailedListener */)
//                .addApi(LocationServices.API)
//                //.addScope(Drive.SCOPE_FILE)
//                .build();

//        if (mGoogleApiClient == null) {
//            mGoogleApiClient = new GoogleApiClient.Builder(this)
//                    .addApi(LocationServices.API)
//                    .build();
//        }

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(mGoogleApiClient, getIndexApiAction());
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mGoogleApiClient, getIndexApiAction());
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
//        mMap.setOnMapClickListener((GoogleMap.OnMapClickListener) this);
//        mMap.setOnMapLongClickListener((GoogleMap.OnMapLongClickListener) this);
//        mMap.setOnMarkerDragListener((GoogleMap.OnMarkerDragListener) this);
//        mMap.setInfoWindowAdapter((GoogleMap.InfoWindowAdapter) this);
        mMap.setOnInfoWindowClickListener(MyOnInfoWindowClickListener);

        // Add a marker in Sydney and move the camera
        LatLng garage01 = new LatLng(48.8618408, 2.475131000000033);
        LatLng garage02 = new LatLng(48.8673028, 2.4697986999999557);
        LatLng garage03 = new LatLng(48.8724459, 2.470011500000055);
        LatLng garage04 = new LatLng(48.876678, 2.4657311999999365);
        LatLng Paris = new LatLng(48.8588871, 2.294486099999972);



        mMap.addMarker(new MarkerOptions().position(garage01)
                .title("Garage D.m.k"))
                .setSnippet("Note : 4/5 Temps d'intervention 18 min");

//        Marker position01 = mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(48.8618408, 2.475131000000033))
//                .title("San Francisco")
//                .snippet("Population: 776733"));

       //mMap.setOnMarkerClickListener(position01.getPosition());

        mMap.addMarker(new MarkerOptions().position(garage02)
                .title("Garage Du Parc"))
                .setSnippet("Note : 3/5 Temps d'intervention 20 min");
        mMap.addMarker(new MarkerOptions().position(garage03)
                .title("Garage Du Fort"))
                .setSnippet("Note : 4.8/5 Temps d'intervention 15 min");
        mMap.addMarker(new MarkerOptions().position(garage04)
                .title("Garage du Coin"))
                .setSnippet("Note : 3.9/5 Temps d'intervention 22 min");

       //mMap.moveCamera(CameraUpdateFactory.newLatLng(Paris));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            Log.d("MapsActivity", "oui");
            System.out.print("OUI");
           //mMap.OnMyLocationButtonClickListener();
 //           mMap.moveCamera(CameraUpdateFactory.newLatLng(mMap.OnMyLocationButtonClick()));
        } else {
            System.out.print("NON");

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.LOCATION_HARDWARE);

        if (requestCode == permissionCheck) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);


            } else {
                // Permission was denied. Display an error message.
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        System.out.print("Oula nan");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        System.out.print("NIQUEL");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                System.out.print("Latitude : " + String.valueOf(mLastLocation.getLatitude()));
                System.out.print("Longitude : " + String.valueOf(mLastLocation.getLongitude()));
            }
        }


    }

    @Override
    public void onConnectionSuspended(int i) {
        System.out.print("Suspendu");
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Maps Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    // @Override
    //public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
      //  if (requestCode == MY_LOCATION_REQUEST_CODE) {
        //    if (permissions.length == 1 &&
          //          permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION &&
            //        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
              //  mMap.setMyLocationEnabled(true);
           // } else {
                // Permission was denied. Display an error message.
           // }
        // }

    public void RetourAccueil()
    {
        Intent intent = new Intent(MapsActivity.this, Home.class);
        startActivity(intent);
    }

    GoogleMap.OnInfoWindowClickListener MyOnInfoWindowClickListener
            = new GoogleMap.OnInfoWindowClickListener(){
        @Override
        public void onInfoWindowClick(Marker marker) {

//            Toast.makeText(MapsActivity.this,
//                    "onInfoWindowClick():\n" +
//                            marker.getPosition().latitude + "\n" +
//                            marker.getPosition().longitude,
//                    Toast.LENGTH_LONG).show();
//            Log.d("TEST", "Click sur l'info bull");

            Intent intent = new Intent(MapsActivity.this, Questionnaire.class);
            startActivity(intent);
        }
    };

}
