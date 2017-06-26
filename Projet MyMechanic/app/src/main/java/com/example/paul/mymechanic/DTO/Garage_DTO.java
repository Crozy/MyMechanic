package com.example.paul.mymechanic.DTO;

/**
 * Created by Leuck on 17/06/2017.
 */

public class Garage_DTO {

    private int id;
    private String nom;
    private String adresse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return adresse;
    }

    public void setLieu(String lieu) {
        this.adresse = lieu;
    }
}
