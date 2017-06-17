package com.example.paul.mymechanic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Console;
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
                SMS.AjoutMarque(MarqueDuVehicule.getSelectedItem().toString());

                final Spinner TypeCommercial = (Spinner) findViewById(R.id.spinnerTypeCommercial);
                SMS.AjoutTypeCommercial(TypeCommercial.getSelectedItem().toString());

                final Spinner AnneeVehicule = (Spinner) findViewById(R.id.spinnerAnnee);
                SMS.AjoutAnnée(AnneeVehicule.getSelectedItem().toString());

                final Spinner Carburant = (Spinner) findViewById(R.id.spinnerCarburant);
                SMS.AjoutCarburant(Carburant.getSelectedItem().toString());

                final Spinner TypeMoteur = (Spinner) findViewById(R.id.spinnerMoteur);
                SMS.AjoutTypeMoteur(TypeMoteur.getSelectedItem().toString());

                Intent intent = new Intent(Questionnaire.this, Questionnaire2.class);
                startActivity(intent);
            }

        });
    }


    public void ComboBox(){

        //Liste Année
        Spinner spinner = (Spinner) findViewById(R.id.spinnerAnnee);
        // Create an ArrayAdapter using the string array and a default spinner layout

        ArrayList anneeList = new ArrayList();

        for(int i=1950; i<2017; i++){
            anneeList.add(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, anneeList);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
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
                       //ArrayList TypeCommercialList = new ArrayList();
                       TypeCommercialList.add("Ibiza");
                       TypeCommercialList.add("Leon");
                       Log.d("Message", "Seat");
                       break;

                   case "Volkswagen":
                       //ArrayList TypeCommercialList = new ArrayList();
                       TypeCommercialList.add("Polo");
                       TypeCommercialList.add("Golf");
                       Log.d("Message", "Volkswagen");
                       break;
                   default:
                       TypeCommercialList.add("Selectionner une marque");
               }
                   //System.out.print("TEST");

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
