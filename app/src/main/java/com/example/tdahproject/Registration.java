package com.example.tdahproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {

    EditText name, username, email, date, password;
    Button confirmRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = (EditText) findViewById(R.id.registerName);
        username = (EditText) findViewById(R.id.registerUsername);
        email = (EditText) findViewById(R.id.registerEmailAdress);
        date = (EditText)findViewById(R.id.registerDateBirth);
        password = (EditText) findViewById(R.id.registerPassword);

        confirmRegistration = (Button) findViewById(R.id.registrationConfirm);

        confirmRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString().trim();
                String newUserName = username.getText().toString().trim();
                String newEmail = email.getText().toString().trim();
                String newDate = date.getText().toString().trim();
                String newPassword = password.getText().toString().trim();

                /* ENVOI DES DONNEES ET ENREGISTREMENT A FIREBASE */





            }
        });
    }
}