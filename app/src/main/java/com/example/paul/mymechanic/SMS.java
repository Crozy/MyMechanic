package com.example.paul.mymechanic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Leuck on 29/05/2017.
 */

public class SMS {

    //static ArrayList Voyant;
    static ArrayList Voyant = new ArrayList();

    static String MarqueVéhicule;
    static String TypeCommercial;
    static String Annee;
    static String carburant;
    static String TypeMoteur;
    static String Probleme;

    static void ViderListeVoyant(){

        for(int i = 0; i < Voyant.size(); i++) {
            Voyant.remove(i);
        }
    }

    static void AjoutVoyant(String VoyantAllume){
        Voyant.add(VoyantAllume);
    }

    static ArrayList GetListeDesVoyant() {
        return Voyant;
    }

    static void AjoutMarque(String Marque) {
        MarqueVéhicule = Marque;
    }

    static String GetMarque(){
        return MarqueVéhicule;
    }

    static void AjoutTypeCommercial(String LeTypeCommercial) {
        TypeCommercial = LeTypeCommercial;
    }

    static String GetTypeCommercial(){
        return TypeCommercial;
    }

    static void AjoutAnnée(String UneAnnee){
        Annee = UneAnnee;
    }

    static String GetAnnee(){
        return Annee;
    }

    static void AjoutCarburant(String LeCarburant){
        carburant = LeCarburant;
    }

    static String GetCarburant(){
        return carburant;
    }

    static void AjoutTypeMoteur(String TypeDuMoteur){
        TypeMoteur = TypeDuMoteur;
    }

    static String GetTypeMoteurt(){
        return TypeMoteur;
    }

    static void AjoutProbleme(String LeProbleme){
        Probleme = LeProbleme;
    }

    static String GetProbleme(){
        return Probleme;
    }
}
