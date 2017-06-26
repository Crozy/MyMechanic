package com.example.paul.mymechanic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.paul.mymechanic.DTO.SMS_DTO;

public class Questionnaire2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire2);


        final Button loginButton = (Button) findViewById(R.id.button3);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SMS_DTO.ViderListeVoyant();

                final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
                if (checkBox.isChecked()) {
                    SMS_DTO.AjoutVoyant("Désembuage lunette arrière");
                }

                final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
                if (checkBox2.isChecked()) {
                    SMS_DTO.AjoutVoyant("Contrôle de traction");
                }

                final CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
                if (checkBox3.isChecked()) {
                    SMS_DTO.AjoutVoyant("Niveau mini de carburant");
                }

                final CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
                if (checkBox4.isChecked()) {
                    SMS_DTO.AjoutVoyant("Antibrouillard avant");
                }

                final CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
                if (checkBox5.isChecked()) {
                    SMS_DTO.AjoutVoyant("Dépollution du moteur");
                }

                final CheckBox checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
                if (checkBox6.isChecked()) {
                    SMS_DTO.AjoutVoyant("Feux de détresse");
                }

                final CheckBox checkBox7 = (CheckBox) findViewById(R.id.checkBox7);
                if (checkBox7.isChecked()) {
                    SMS_DTO.AjoutVoyant("Défaillance des freins");
                }

                final CheckBox checkBox8 = (CheckBox) findViewById(R.id.checkBox8);
                if (checkBox8.isChecked()) {
                    SMS_DTO.AjoutVoyant("Pression d'huile moteur");
                }

                final CheckBox checkBox9 = (CheckBox) findViewById(R.id.checkBox9);
                if (checkBox9.isChecked()) {
                    SMS_DTO.AjoutVoyant("Charge batterie");
                }


                Intent intent = new Intent(Questionnaire2.this, Questionnaire3.class);
                startActivity(intent);
            }

        });

    }
}
