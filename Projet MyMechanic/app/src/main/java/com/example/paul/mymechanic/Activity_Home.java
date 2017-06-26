package com.example.paul.mymechanic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Activity_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button loginButton = (Button) findViewById(R.id.button4);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Home.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        final Button loginButtonTest = (Button) findViewById(R.id.button5);
        loginButtonTest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Home.this, Activity_GarageList.class);
                startActivity(intent);
            }
        });
    }}


