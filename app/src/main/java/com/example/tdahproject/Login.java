package com.example.tdahproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {


    EditText userName, password;
    Button connect, register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.loginID);
        password = (EditText) findViewById(R.id.loginPassword);
        connect = (Button) findViewById(R.id.loginConnect);
        register = (Button) findViewById(R.id.loginRegister);

        String adminName = new String("Admin");
        String adminPassword= new String("ABC");

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().equals(adminName) && password.getText().toString().equals(adminPassword)){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
            }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });
    }
}