package com.example.tdahproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MenuFragment extends Fragment {

    View view;
    private Button taskCreate;
    private TextView welcoming;
    public static humain currentUser;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_menu, container, false );

        //Recuperation de l'utilisateur actuel
        currentUser = MainActivity.getCurrentUser();

        taskCreate = (Button) view.findViewById(R.id.task_create);
        welcoming = (TextView) view.findViewById(R.id.textViewWelcome);

        welcoming.setText("Welcome, " + currentUser.getUsername());

        taskCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), taskCreation.class);
                startActivity(intent);

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}