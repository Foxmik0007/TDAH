package com.example.tdahproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class loadingToLobby extends AppCompatActivity {


    DatabaseReference USERS;
    public static humain currentUser;
    public ArrayList<humain> listOfUser = new ArrayList<humain>();
    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_loading_to_lobby );

        number = (TextView) findViewById( R.id.numberOfUser );


        int nu = listOfUser.size();

    }
}