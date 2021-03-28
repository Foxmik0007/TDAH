package com.example.tdahproject;

public class tache {
   private String nom;
   private String durée;
   private String statut = "In progress";
   private String dateFin;
   private String nomObjectif;


    public tache() {
    }

    public tache(String nom, String durée, String nomObjectif) {
        this.nom = nom;
        this.durée = durée;
        this.nomObjectif = nomObjectif;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getNomObjectif() {
        return nomObjectif;
    }

    public void setNomObjectif(String nomObjectif) {
        this.nomObjectif = nomObjectif;
    }
}
