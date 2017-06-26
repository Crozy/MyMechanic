package com.example.paul.mymechanic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.paul.mymechanic.DTO.Garage_DTO;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Activity_GarageList extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<String> ListeGarage = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_garage);

        mListView = (ListView) findViewById(R.id.listView);

        appelAsynchrone();
    }

    /////////////////////////////////////////////PARTIE API//////////////////////////////////////////////:

    public void appelAsynchrone() {

        AppelAPIService githubService = new RestAdapter.Builder()
                .setEndpoint(AppelAPIService.ENDPOINT)
                .build()
                .create(AppelAPIService.class);

        githubService.listReposAsync(new Callback<List<Garage_DTO>>() {
            @Override
            public void success(List<Garage_DTO> repos, Response response) {
                afficherRepos(repos);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Error", "Probleme");
            }


        });
    }

    public void afficherRepos(List<Garage_DTO> repos) {

        for (Garage_DTO repo: repos)
        {
            ListeGarage.add(repo.getNom());
        }


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity_GarageList.this,
                android.R.layout.simple_list_item_1, ListeGarage);
        mListView.setAdapter(adapter);
    }


    public void notAllowed() {
        Toast.makeText(this, "Impossible d'effectuer cette action", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<String> ListeDesGarages(){

        return ListeGarage;
    }
}
