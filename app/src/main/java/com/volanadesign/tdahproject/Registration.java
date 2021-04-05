package com.volanadesign.tdahproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    private   EditText name, username, email, date, password;
    private Button confirmRegistration;
    DatabaseReference registrationDatabase;

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

        registrationDatabase = FirebaseDatabase.getInstance().getReference("UserInformation");

        confirmRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString().trim();
                String newUserName = username.getText().toString().trim();
                String newEmail = email.getText().toString().trim();
                String newDate = date.getText().toString().trim();
                String newPassword = password.getText().toString().trim();

                //Gestion des erreurs
                if (newName.equals(null) || newUserName.equals(null) || newEmail.equals(null) || newDate.equals(null) || newPassword.equals(null))
                    Toast.makeText(Registration.this, "Error - Please review your registration", Toast.LENGTH_SHORT).show();
                else{

                    //Enregistrement de l'utilisateur
                    humain newUser = new humain(newName, newUserName, newEmail, newDate, newPassword, 1, 0);
                    registrationDatabase.child(newUserName).setValue(newUser);
                    Toast.makeText(Registration.this, "Inscription Validée", Toast.LENGTH_SHORT).show();

                    /*Validation de l'inscription*/

                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity( intent );
                }
            }
        });
    }
}