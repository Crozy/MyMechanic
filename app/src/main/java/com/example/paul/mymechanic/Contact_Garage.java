package com.example.paul.mymechanic;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.Marker;

public class Contact_Garage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__garage);
    }

    public void Appel(View View) {
        Intent appel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0102030405"));
        startActivity(appel);
    }

    public void QuestionPanne(View View) {
        Intent intent = new Intent(Contact_Garage.this, Questionnaire.class);
        startActivity(intent);
    }

}
