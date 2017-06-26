package com.example.paul.mymechanic.DTO;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Leuck on 29/05/2017.
 */

public class SMS_DTO {

    static ArrayList Voyant = new ArrayList();

    static String MarqueVéhicule;
    static String TypeCommercial;
    static String Annee;
    static String carburant;
    static String TypeMoteur;
    static String Probleme;

    public static void ViderListeVoyant(){

        for(int i = 0; i < Voyant.size(); i++) {
            Voyant.remove(i);
        }
    }

    public static void AjoutVoyant(String VoyantAllume){
        Voyant.add(VoyantAllume);
    }

    public static ArrayList GetListeDesVoyant() {
        return Voyant;
    }

    public static void AjoutMarque(String Marque) {
        MarqueVéhicule = Marque;
    }

    public static String GetMarque(){
        return MarqueVéhicule;
    }

    public static void AjoutTypeCommercial(String LeTypeCommercial) {
        TypeCommercial = LeTypeCommercial;
    }

    public static String GetTypeCommercial(){
        return TypeCommercial;
    }

    public static void AjoutAnnée(String UneAnnee){
        Annee = UneAnnee;
    }

    public static String GetAnnee(){
        return Annee;
    }

    public static void AjoutCarburant(String LeCarburant){
        carburant = LeCarburant;
    }

    public static String GetCarburant(){
        return carburant;
    }

    public static void AjoutTypeMoteur(String TypeDuMoteur){
        TypeMoteur = TypeDuMoteur;
    }

    public static String GetTypeMoteurt(){
        return TypeMoteur;
    }

    public static void AjoutProbleme(String LeProbleme){
        Probleme = LeProbleme;
    }

    public static String GetProbleme(){
        return Probleme;
    }
}
