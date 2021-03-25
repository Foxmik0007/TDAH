package com.example.tdahproject;

import java.util.ArrayList;

public class Objectif {

    private String nom;
    private String difficulté;
    private String importance;
    ArrayList<tache> listeDeTache;
    private String dateDeCreation;
    private String dueDate;
    int progression;
    private tache nextStep;
    private String etat;
    private humain partner;

    private Message messagerie;

    public Objectif() {
    }

    public Objectif(String nom, String difficulté, String importance, ArrayList<tache> listeDeTache, String dateDeCreation, int progression, String etat, Message messagerie, String dueDate) {
        this.nom = nom;
        this.difficulté = difficulté;
        this.importance = importance;
        this.listeDeTache = listeDeTache;
        this.dateDeCreation = dateDeCreation;
        this.dueDate = dueDate;
        this.progression = progression;
        this.etat = etat;
        this.messagerie = messagerie;

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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getProgression() {
        return progression;
    }

    public void setProgression(int progression) {
        this.progression = progression;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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

    public void setNextStep(tache nextStep) {
        this.nextStep = nextStep;
    }

    public Message getMessagerie() {
        return messagerie;
    }


    public void setMessagerie(Message messagerie) {
        this.messagerie = messagerie;
    }

    public humain getPartner() {
        return partner;
    }

    public void setPartner(humain partner) {
        this.partner = partner;
    }
}
