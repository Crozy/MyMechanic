package com.example.paul.mymechanic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class Questionnaire2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire2);

//        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
//        if (checkBox.isChecked()) {
//            //checkBox.setChecked(false);
//            Toast.makeText(this, "YOUHOU 2 ! ", Toast.LENGTH_SHORT).show();
//            Log.d("SiOuiChekBox1", "Cocher");
//        }
//        else{
//            Log.d("SiNonChekBox1", "Décocher");
//        }

        final Button loginButton = (Button) findViewById(R.id.button3);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //SMS PanneSMS = new SMS();

                final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
                if (checkBox.isChecked()) {
                    //checkBox.setChecked(false);
                    //Log.d("SiOuiChekBox1", "Cocher");
                    SMS.AjoutVoyant("Désembuage lunette arrière");
                }

                final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
                if (checkBox2.isChecked()) {
                    //checkBox.setChecked(false);
                    //Log.d("SiOuiChekBox1", "Cocher");
                    SMS.AjoutVoyant("Contrôle de traction");
                }

                final CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox2);
                if (checkBox3.isChecked()) {
                    //checkBox.setChecked(false);
                    //Log.d("SiOuiChekBox1", "Cocher");
                    SMS.AjoutVoyant("Niveau mini de carburant");
                }

                final CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox2);
                if (checkBox4.isChecked()) {
                    //checkBox.setChecked(false);
                    //Log.d("SiOuiChekBox1", "Cocher");
                    SMS.AjoutVoyant("Antibrouillard avant");
                }

                final CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox2);
                if (checkBox5.isChecked()) {
                    //checkBox.setChecked(false);
                    //Log.d("SiOuiChekBox1", "Cocher");
                    SMS.AjoutVoyant("Dépollution du moteur");
                }

                final CheckBox checkBox6 = (CheckBox) findViewById(R.id.checkBox2);
                if (checkBox6.isChecked()) {
                    //checkBox.setChecked(false);
                    //Log.d("SiOuiChekBox1", "Cocher");
                    SMS.AjoutVoyant("Feux de détresse");
                }

                final CheckBox checkBox7 = (CheckBox) findViewById(R.id.checkBox2);
                if (checkBox7.isChecked()) {
                    //checkBox.setChecked(false);
                    //Log.d("SiOuiChekBox1", "Cocher");
                    SMS.AjoutVoyant("Défaillance des freins");
                }

                final CheckBox checkBox8 = (CheckBox) findViewById(R.id.checkBox2);
                if (checkBox8.isChecked()) {
                    //checkBox.setChecked(false);
                    //Log.d("SiOuiChekBox1", "Cocher");
                    SMS.AjoutVoyant("Pression d'huile moteur");
                }

                final CheckBox checkBox9 = (CheckBox) findViewById(R.id.checkBox2);
                if (checkBox9.isChecked()) {
                    //checkBox.setChecked(false);
                    //Log.d("SiOuiChekBox1", "Cocher");
                    SMS.AjoutVoyant("Charge batterie");
                }

//                boolean checked = ((CheckBox) v).isChecked();
//
//                SMS PanneSMS = new SMS();
//
//                switch(v.getId()) {
//                    case R.id.checkBox1:
//                        if (checked) {
//                            PanneSMS.AjoutVoyant("yop");
//                        } else {
//                            break;
//                        }
//                }

                Intent intent = new Intent(Questionnaire2.this, Questionnaire3.class);
                startActivity(intent);
            }

        });

    }

//    public void VoyantActif(View view){
//        boolean checked = ((CheckBox) view).isChecked();
//
//        // Check which checkbox was clicked
//        switch(view.getId()) {
//            case R.id.checkBox1 :
//                if (checked) {
//                    Toast.makeText(this, "YOUHOU ! ", Toast.LENGTH_SHORT).show();
//                }
//            else {
//                    //Toast.makeText(this,"PAS YOUHOU ! ",Toast.LENGTH_SHORT).show();
//                break; }
//            case R.id.checkBox2 :
//                if (checked) {
//                    Toast.makeText(this, "YOUHOU 2 ! ", Toast.LENGTH_SHORT).show();
//                }
//            else {
//                // I'm lactose intolerant
//                break; }
//
//            case R.id.checkBox3 :
//                if (checked) {
//                    Toast.makeText(this, "YOUHOU ! ", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    //Toast.makeText(this,"PAS YOUHOU ! ",Toast.LENGTH_SHORT).show();
//                    break; }
//            case R.id.checkBox4 :
//                if (checked) {
//                    Toast.makeText(this, "YOUHOU 2 ! ", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    // I'm lactose intolerant
//                    break; }
//
//            case R.id.checkBox5 :
//                if (checked) {
//                    Toast.makeText(this, "YOUHOU ! ", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    //Toast.makeText(this,"PAS YOUHOU ! ",Toast.LENGTH_SHORT).show();
//                    break; }
//            case R.id.checkBox6 :
//                if (checked) {
//                    Toast.makeText(this, "YOUHOU 2 ! ", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    // I'm lactose intolerant
//                    break; }
//
//            case R.id.checkBox7 :
//                if (checked) {
//                    Toast.makeText(this, "YOUHOU 2 ! ", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    // I'm lactose intolerant
//                    break; }
//
//            case R.id.checkBox8 :
//                if (checked) {
//                    Toast.makeText(this, "YOUHOU 2 ! ", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    // I'm lactose intolerant
//                    break; }
//
//            case R.id.checkBox9 :
//                if (checked) {
//                    Toast.makeText(this, "YOUHOU 2 ! ", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    // I'm lactose intolerant
//                    break; }
//        }
//    }

//    public class MyActivity extends Activity {
//        protected void onCreate(Bundle icicle) {
//            super.onCreate(icicle);
//
//            setContentView(R.layout.activity_questionnaire2);
//
//            final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
//            if (checkBox.isChecked()) {
//                //checkBox.setChecked(false);
//                Toast.makeText(this, "YOUHOU 2 ! ", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }



}
