package com.example.tdahproject;

import java.util.ArrayList;

public class Objectif {

    private String nom;
    private String difficulté;
    private String importance;
    ArrayList<tache> listeDeTache;
    private String dateDeCreation;
    short progression = 0;

    public Objectif() {
    }

    public Objectif(String nom, String difficulté, String importance, ArrayList<tache> listeDeTache, String dateDeCreation) {
        this.nom = nom;
        this.difficulté = difficulté;
        this.importance = importance;
        this.listeDeTache = listeDeTache;
        this.dateDeCreation = dateDeCreation;
    }

//temporaire
    public Objectif(String nom, String difficulté) {
        this.nom = nom;
        this.difficulté = difficulté;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDifficulté() {
        return difficulté;
    }

    public void setDifficulté(String difficulté) {
        this.difficulté = difficulté;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public ArrayList<tache> getListeDeTache() {
        return listeDeTache;
    }

    public void setListeDeTache(ArrayList<tache> listeDeTache) {
        this.listeDeTache = listeDeTache;
    }

    public String getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(String dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public short getProgression() {
        return progression;
    }

    public void setProgression(short progression) {
        this.progression = progression;
    }

    public tache getNextStep (){
        tache prox = new tache();
        for (short i = 0; i < listeDeTache.size(); i++){
            if (listeDeTache.get(i).getStatut().equals("In progress")){
                prox = listeDeTache.get(i);
                break;
            }
        }
        return  prox;
    }

}
