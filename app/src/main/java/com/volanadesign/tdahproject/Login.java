package com.volanadesign.tdahproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Login extends AppCompatActivity {


    private EditText userName, password;
    private Button connect, register;
    private TextView errorMessage;

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
        errorMessage = (TextView) findViewById(R.id.errorMessage);

        String adminName = new String("Admin");
        String adminPassword= new String("ABC");

        humain Admin = new humain(adminName,adminPassword);

        //Transfert de la base de donnees locale vers activité suivante
        listUser = loadingToLogin.getListeUtilisateur();

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean verify = false;

                //Recuperation des données saisies
                String idConnect = userName.getText().toString();
                String idpassword = password.getText().toString();

                // Verification profil utilisateur Si administrateur
                if (idConnect.equals(adminName) && idpassword.equals(adminPassword)) {
                    Intent intent = new Intent( getApplicationContext(), loadingToLobby.class );
                    currentUser = Admin;
                    verify = true;
                    startActivity( intent );
                }

                //Creation d'un utilisateur provisoire
                currentUser = new humain(idConnect, idpassword);
                
                //Verification profil utilisateur si present
                for (short i = 0; i < listUser.size(); i++){
                    if (currentUser.getUsername().equals(listUser.get(i).getUsername()) &&
                            currentUser.getPassword().equals( listUser.get(i).getPassword() )) {
                        verify = true;
                        Intent intent = new Intent(getApplicationContext(), loadingToLobby.class);
                        startActivity(intent);
                    }
                }

                if(!verify){
                    errorMessage.setText("Username or Incorrect password \n \t Please, try again");
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