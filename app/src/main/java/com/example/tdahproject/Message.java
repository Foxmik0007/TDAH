package com.example.tdahproject;

public class Message {

    String contenu;
    humain emetteur;
    humain destinataire;

    public Message() {
    }

    public Message(String contenu, humain emetteur, humain destinataire) {
        this.contenu = contenu;
        this.emetteur = emetteur;
        this.destinataire = destinataire;
    }



    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public humain getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(humain emetteur) {
        this.emetteur = emetteur;
    }

    public humain getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(humain destinataire) {
        this.destinataire = destinataire;
    }
}
