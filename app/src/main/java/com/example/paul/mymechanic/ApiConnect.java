//package com.example.paul.mymechanic;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.app.ActionBarActivity;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationServices;
//
//public class ApiConnect extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
//
//
//    protected synchronized void buildGoogleApiClient() {
//        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//    }
//
////    protected void onStart() {
////        mGoogleApiClient.connect();
////        super.onStart();
////    }
////
////    protected void onStop() {
////        mGoogleApiClient.disconnect();
////        super.onStop();
////    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        super.onStart();
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//}
