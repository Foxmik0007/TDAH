package com.example.tdahproject;

public class tache {
    String nom;
    String durée;

    public tache() {
    }

    public tache(String nom, String durée) {
        this.nom = nom;
        this.durée = durée;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDurée() {
        return durée;
    }

    public void setDurée(String durée) {
        this.durée = durée;
    }
}
