package com.example.tdahproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class partnerSelect extends AppCompatActivity {


    public static humain currentUser = MainActivity.getCurrentUser();
    public static ArrayList<humain> ListeDesUtilisateurs = new ArrayList<humain>();
    private RecyclerView recyclerView;
    RecyclerViewContact adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_select);

        ListeDesUtilisateurs = loadingToLobby.getListeDesUtilisateurs();

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewContact);
        adapter= new RecyclerViewContact(this, ListeDesUtilisateurs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



    }
}