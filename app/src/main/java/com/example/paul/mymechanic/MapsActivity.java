package com.example.paul.mymechanic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .addApi(AppIndex.API).build();
        }

        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
        AppIndex.AppIndexApi.start(mGoogleApiClient, getIndexApiAction());
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
        AppIndex.AppIndexApi.end(mGoogleApiClient, getIndexApiAction());
    }







    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(MyOnInfoWindowClickListener);

        LatLng garage01 = new LatLng(48.8618408, 2.475131000000033);
        LatLng garage02 = new LatLng(48.8673028, 2.4697986999999557);
        LatLng garage03 = new LatLng(48.8724459, 2.470011500000055);
        LatLng garage04 = new LatLng(48.876678, 2.4657311999999365);
        LatLng Paris = new LatLng(48.8588871, 2.294486099999972);



        mMap.addMarker(new MarkerOptions().position(garage01)
                .title("Garage D.m.k"))
                .setSnippet("Note : 4/5 Temps d'intervention 18 min");


        mMap.addMarker(new MarkerOptions().position(garage02)
                .title("Garage Du Parc"))
                .setSnippet("Note : 3/5 Temps d'intervention 20 min");
        mMap.addMarker(new MarkerOptions().position(garage03)
                .title("Garage Du Fort"))
                .setSnippet("Note : 4.8/5 Temps d'intervention 15 min");
        mMap.addMarker(new MarkerOptions().position(garage04)
                .title("Garage du Coin"))
                .setSnippet("Note : 3.9/5 Temps d'intervention 22 min");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            Log.d("MapsActivity", "oui");
            System.out.print("OUI");
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

    public void RetourAccueil()
    {
        Intent intent = new Intent(MapsActivity.this, Activity_Home.class);
        startActivity(intent);
    }

    GoogleMap.OnInfoWindowClickListener MyOnInfoWindowClickListener
            = new GoogleMap.OnInfoWindowClickListener(){
        @Override
        public void onInfoWindowClick(Marker marker) {

            Intent intent = new Intent(MapsActivity.this, Activity_Contact_Garage.class);
            startActivity(intent);
        }
    };

}
