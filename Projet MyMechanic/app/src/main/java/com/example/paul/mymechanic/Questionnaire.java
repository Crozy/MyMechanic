package com.example.paul.mymechanic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.paul.mymechanic.DTO.SMS_DTO;

import java.util.ArrayList;

public class Questionnaire extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        ComboBox();

        final Button loginButton = (Button) findViewById(R.id.button2);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Spinner MarqueDuVehicule = (Spinner) findViewById(R.id.spinnerMarque);
                SMS_DTO.AjoutMarque(MarqueDuVehicule.getSelectedItem().toString());

                final Spinner TypeCommercial = (Spinner) findViewById(R.id.spinnerTypeCommercial);
                SMS_DTO.AjoutTypeCommercial(TypeCommercial.getSelectedItem().toString());

                final Spinner AnneeVehicule = (Spinner) findViewById(R.id.spinnerAnnee);
                SMS_DTO.AjoutAnnée(AnneeVehicule.getSelectedItem().toString());

                final Spinner Carburant = (Spinner) findViewById(R.id.spinnerCarburant);
                SMS_DTO.AjoutCarburant(Carburant.getSelectedItem().toString());

                final Spinner TypeMoteur = (Spinner) findViewById(R.id.spinnerMoteur);
                SMS_DTO.AjoutTypeMoteur(TypeMoteur.getSelectedItem().toString());

                Intent intent = new Intent(Questionnaire.this, Questionnaire2.class);
                startActivity(intent);
            }

        });
    }


    public void ComboBox(){

        //Liste Année
        Spinner spinner = (Spinner) findViewById(R.id.spinnerAnnee);

        ArrayList anneeList = new ArrayList();

        for(int i=1950; i<2017; i++){
            anneeList.add(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, anneeList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Spinner Marque = (Spinner) findViewById(R.id.spinnerMarque);
        Marque.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {



            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("Message", "GOOD");

                Spinner Marque = (Spinner) findViewById(R.id.spinnerMarque);
                Spinner TypeCommercial = (Spinner) findViewById(R.id.spinnerTypeCommercial);
                String LaMarque;

                LaMarque = Marque.getSelectedItem().toString();

                ArrayList TypeCommercialList = new ArrayList();

               switch(LaMarque) {

                   case "Seat":
                       TypeCommercialList.add("Ibiza");
                       TypeCommercialList.add("Leon");
                       Log.d("Message", "Seat");
                       break;

                   case "Volkswagen":
                       TypeCommercialList.add("Polo");
                       TypeCommercialList.add("Golf");
                       Log.d("Message", "Volkswagen");
                       break;
                   default:
                       TypeCommercialList.add("Selectionner une marque");
               }

                   ArrayAdapter<String> adapterTypeCommercial = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, TypeCommercialList);
                   adapterTypeCommercial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   TypeCommercial.setAdapter(adapterTypeCommercial);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Log.d("Message", "PAS GOOD");
            }

        });

    }







}
