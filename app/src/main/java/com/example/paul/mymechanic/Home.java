package com.example.paul.mymechanic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //////////////////////////////////////////////////////////////
//        appelAsynchrone();
        ////////////////////////////////////////////////////////////////////////////

        final Button loginButton = (Button) findViewById(R.id.button4);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MapsActivity.class);
                startActivity(intent);
            }
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
        });

        final Button loginButtonTest = (Button) findViewById(R.id.button5);
        loginButtonTest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ListeGarage.class);
                startActivity(intent);
            }
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
//    private void appelAsynchrone() {
//        AppelAPI githubService = new RestAdapter.Builder()
//                .setEndpoint(AppelAPI.ENDPOINT)
//                .build()
//                .create(AppelAPI.class);
//
//        githubService.listReposAsync(new Callback<List<Garage>>() {
//            @Override
//            public void success(List<Garage> repos, Response response) {
//                afficherRepos(repos);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.d("MapsActivity", "Probleme");
//            }
//        });
//    }
//
//        public void afficherRepos(List<Garage> repos) {
//            String test = "";
//
//            for (Garage repo: repos)
//            {
//                test = test +" "+ repo.getId();
//
//            }
//
//
//            //Toast.makeText(this, "nombre de dépots : " + repos.size(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "Liste des dépots : " + test, Toast.LENGTH_SHORT).show();
//        }
//
//        public void notAllowed() {
//            Toast.makeText(this, "Impossible d'effectuer cette action", Toast.LENGTH_SHORT).show();
//        }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////



    }


