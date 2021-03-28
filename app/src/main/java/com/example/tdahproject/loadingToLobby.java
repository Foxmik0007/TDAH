package com.example.tdahproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class loadingToLobby extends AppCompatActivity {


    DatabaseReference USERSGOALS;
    DatabaseReference USERSLIST;
    DatabaseReference USERPREVIOUSTASK;
    DatabaseReference USERNEXTTASK;

    public static humain currentUser = Login.getCurrentUser();
    public static ArrayList<humain> listeDesUtilisateurs = new ArrayList<humain>();
    public static ArrayList<Objectif> listDesObjectifs= new ArrayList<Objectif>();
    public static ArrayList<tache> previousListeTache = new ArrayList<tache>();
    public static ArrayList<tache> nextListeTache = new ArrayList<tache>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_loading_to_lobby );

        //Liaison à la bonne liste des données
        USERSGOALS = FirebaseDatabase.getInstance().getReference("Données Utilisateur/" + currentUser.getUsername() + "/Liste des Objectifs");
        USERSLIST = FirebaseDatabase.getInstance().getReference("UserInformation");
        previousCompleteTab(previousListeTache, listDesObjectifs);

        Handler handler = new Handler();

        //CONFIGURATION DELAIS D'ATTENTE
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        }, 1250);
    }


    // Gestion de la base de donnée
    @Override
    protected void onStart() {
        super.onStart();

        USERSGOALS.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Objectif objectif = snapshot.getValue(Objectif.class);
                    Boolean verify = false;

                    for (short i = 0; i < listDesObjectifs.size(); i++){
                        if (objectif.getNom() == listDesObjectifs.get(i).getNom())
                            verify = true;
                    }
                    if (!verify)
                        listDesObjectifs.add(objectif);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        USERSLIST.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    humain humain = dataSnapshot.getValue(humain.class);
                    Boolean verify = false;

                    for (short i = 0; i < listeDesUtilisateurs.size(); i++){
                        if (humain.getName() == listeDesUtilisateurs.get(i).getName())
                            verify = true;
                    }
                    if (!verify)
                        listeDesUtilisateurs.add(humain);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    //Date Getters
    private String getCurrentDateString(){
        Calendar date = Calendar.getInstance();
        String currentDate =  DateFormat.getDateInstance(DateFormat.FULL).toString();

        return currentDate;
    }
    private String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }
    private String getTomorrowDateString() {
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);
        return dateFormat.format(cal.getTime());
    }

    //Getters
    public static humain getCurrentUser() {
        return currentUser;
    }

    public static void setListDesObjectifs(ArrayList<Objectif> listDesObjectifs) {
        loadingToLobby.listDesObjectifs = listDesObjectifs;
    }

    public static ArrayList<Objectif> getListDesObjectifs() {
        return listDesObjectifs;
    }

    public static ArrayList<humain> getListeDesUtilisateurs() {
        return listeDesUtilisateurs;
    }

    public static void setListeDesUtilisateurs(ArrayList<humain> listeDesUtilisateurs) {
        loadingToLobby.listeDesUtilisateurs = listeDesUtilisateurs;
    }

    public void previousCompleteTab (ArrayList<tache> listeDeTache, ArrayList<Objectif> listeObjectif){
        for (short i = 0; i < listeObjectif.size(); i++){
            for (short k = 0; i< listeObjectif.get(i).getListeDeTache().size(); k++){
                if (listeObjectif.get(i).getListeDeTache().get(k).getDateFin().equals(getYesterdayDateString()))
                    listeDeTache.add(listeObjectif.get(i).getListeDeTache().get(k));
            }
        }
    }
}