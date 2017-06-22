package com.example.paul.mymechanic;

import android.telecom.Call;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;


/**
 * Created by Leuck on 17/06/2017.
 */

public interface AppelAPI {

    public static final String ENDPOINT = "http://192.168.43.115:8080";

    @Headers("Cache-Control: max-age=640000")
    @GET("/api/garages")
    List<Garage> listRepos();

    @GET("/api/garages")
    void listReposAsync(Callback<List<Garage>> callback);

    @GET("/search/repositories")
    List<Garage> searchRepos(@Query("q") String query);
}
