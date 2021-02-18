package com.example.tdahproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {


    private EditText userName, password;
    private Button connect, register;

    private static humain currentUser;

    private static ArrayList<humain> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.loginID);
        password = (EditText) findViewById(R.id.loginPassword);
        connect = (Button) findViewById(R.id.loginConnect);
        register = (Button) findViewById(R.id.loginRegister);
        currentUser = new humain();

        String adminName = new String("Admin");
        String adminPassword= new String("ABC");

        //Transfert de la base de donnees locale vers activité suivante
        listUser = loadingToLogin.getListeUtilisateur();

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Recuperation des données saisies
                String idConnect = userName.getText().toString();
                String idpassword = password.getText().toString();

                // Verification profil utilisateur Si administrateur
                if (idConnect.equals(adminName) && idpassword.equals(adminPassword)) {
                    Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                    startActivity( intent );
                }

                //Creation d'un utilisateur provisoire
                currentUser.setUsername( idConnect );
                currentUser.setPassword( idpassword );

                //Verification profil utilisateur si present
                for (short i = 0; i < listUser.size(); i++){
                    if (currentUser.getUsername().equals(listUser.get(i).getUsername()) &&
                            currentUser.getPassword().equals( listUser.get(i).getPassword() )) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });

        //Creer un compte utlisateur
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });

    }

    public static humain getCurrentUser() {
        return currentUser;
    }

}