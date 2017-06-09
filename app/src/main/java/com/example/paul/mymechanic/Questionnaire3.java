package com.example.paul.mymechanic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Questionnaire3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }

//        final Button loginButton = (Button) findViewById(R.id.button_Demande_Intervention);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Questionnaire3.this, Contact_Garage.class);
//                startActivity(intent);
//            }
//
//        });


//        final Button loginButton = (Button) findViewById(R.id.button_Demande_Intervention);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                final Spinner Probleme = (Spinner) findViewById(R.id.spinnerProbleme);
//                SMS.AjoutProbleme(Probleme.getSelectedItem().toString());
//            }
//
//        });
//
    }

    public void EnvoyéDevisSMS(View View) {

        final Spinner Probleme = (Spinner) findViewById(R.id.spinnerProbleme);
        SMS.AjoutProbleme(Probleme.getSelectedItem().toString());

        String ListeDesVoyan;

        ListeDesVoyan = "Voyant allumé : ";

        for(int i = 0; i < SMS.GetListeDesVoyant().size(); i++) {
            ListeDesVoyan = ListeDesVoyan +" \n "+ SMS.GetListeDesVoyant().get(i);
        }

        Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:0102030405")); {
        sms.putExtra("sms_body", "Marque : "+SMS.GetMarque()+"\n Type Commercial : "+SMS.GetTypeCommercial()+"\n Type Moteur : "+SMS.GetTypeMoteurt()+"\n Année : "+SMS.GetAnnee()+"\n Carburant : "+SMS.GetCarburant()+"\n"+ListeDesVoyan+"\n Problème rencontré : "+SMS.GetProbleme());
        startActivity(sms);
    }

}

}

