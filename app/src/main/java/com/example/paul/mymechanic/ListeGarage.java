package com.example.paul.mymechanic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListeGarage extends AppCompatActivity {

    private ListView mListView;
    ArrayList<String> ListeGarage = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_garage);

        mListView = (ListView) findViewById(R.id.listView);


        appelAsynchrone();
    }

    /////////////////////////////////////////////PARTIE API//////////////////////////////////////////////:

    private void appelAsynchrone() {
        AppelAPI githubService = new RestAdapter.Builder()
                .setEndpoint(AppelAPI.ENDPOINT)
                .build()
                .create(AppelAPI.class);

        githubService.listReposAsync(new Callback<List<Garage>>() {
            @Override
            public void success(List<Garage> repos, Response response) {
                afficherRepos(repos);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("MapsActivity", "Probleme");
            }
        });
    }

    public void afficherRepos(List<Garage> repos) {
        String test = "";

        for (Garage repo: repos)
        {
           // test = test +" "+ repo.getNom();
            ListeGarage.add(repo.getNom());

        }


        //Toast.makeText(this, "nombre de dépots : " + repos.size(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Liste des dépots : " + test, Toast.LENGTH_SHORT).show();


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListeGarage.this,
                android.R.layout.simple_list_item_1, ListeGarage);
        mListView.setAdapter(adapter);

    }

    public void notAllowed() {
        Toast.makeText(this, "Impossible d'effectuer cette action", Toast.LENGTH_SHORT).show();
    }
}
