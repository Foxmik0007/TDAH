package com.example.tdahproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class loadingToLogin extends AppCompatActivity {


    //Creation base de donnees locale
    public static ArrayList<humain> listeUtilisateur = new ArrayList<humain>();
    private DatabaseReference loginDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_loading_to_login );

        //Instanciation de la base de donnee
        loginDatabase = FirebaseDatabase.getInstance().getReference("UserInformation");

        Handler handler = new Handler();

        //CONFIGURATION DELAIS D'ATTENTE
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }

        }, 1250);
    }

    // Gestion de la base de donnée
    @Override
    protected void onStart() {
        super.onStart();

        loginDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    humain personne = snapshot.getValue(humain.class);
                    Boolean verify = false;

                    for (short i = 0; i < listeUtilisateur.size(); i++){
                        if (personne.getUsername() == listeUtilisateur.get(i).getUsername())
                            verify = true;
                    }
                    if (!verify)
                        listeUtilisateur.add(personne);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static ArrayList<humain> getListeUtilisateur() {
        return listeUtilisateur;
    }
}