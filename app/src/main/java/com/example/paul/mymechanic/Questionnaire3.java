package com.example.paul.mymechanic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

import com.example.paul.mymechanic.DTO.SMS_DTO;

import org.json.JSONException;

public class Questionnaire3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void EnvoyéDevisSMS(View View) throws JSONException {

        final Spinner Probleme = (Spinner) findViewById(R.id.spinnerProbleme);
        SMS_DTO.AjoutProbleme(Probleme.getSelectedItem().toString());
        String ListeDesVoyan;

        ListeDesVoyan = "Voyant allumé : ";

        for(int i = 0; i < SMS_DTO.GetListeDesVoyant().size(); i++) {
            ListeDesVoyan = ListeDesVoyan +" \n "+ SMS_DTO.GetListeDesVoyant().get(i);
        }

        Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:0102030405")); {
        sms.putExtra("sms_body", "Marque : "+ SMS_DTO.GetMarque()+"\n Type Commercial : "+ SMS_DTO.GetTypeCommercial()+"\n Type Moteur : "+ SMS_DTO.GetTypeMoteurt()+"\n Année : "+ SMS_DTO.GetAnnee()+"\n Carburant : "+ SMS_DTO.GetCarburant()+"\n"+ListeDesVoyan+"\n Problème rencontré : "+ SMS_DTO.GetProbleme());
        startActivity(sms);
    }

    }
}

