package com.volanadesign.tdahproject;

public class humain {
    private String name;
    private String username;
    private String email;
    private String birthday;
    private String password;
    private String objectifEnCours = null;
    private humain partenaire = null;
    private int niveau;
    private int experience ;

    public humain() {
    }

    public humain(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public humain(String name, String username, String email, String birthday, String password, int niveau, int experience) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
        this.niveau = niveau;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getObjectifEnCours() {
        return objectifEnCours;
    }

    public void setObjectifEnCours(String objectifEnCours) {
        this.objectifEnCours = objectifEnCours;
    }

    public humain getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(humain partenaire) {
        this.partenaire = partenaire;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
