package com.example.tdahproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class loadingToLobby extends AppCompatActivity {


    DatabaseReference USERSGOALS;
    public static humain currentUser = Login.getCurrentUser();
    public static ArrayList<Objectif> listDesObjectifs= new ArrayList<Objectif>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_loading_to_lobby );

        //Liaison à la bonne liste des données
        USERSGOALS = FirebaseDatabase.getInstance().getReference("Données Utilisateur/" + currentUser.getUsername() + "/Liste des Objectifs");


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
}