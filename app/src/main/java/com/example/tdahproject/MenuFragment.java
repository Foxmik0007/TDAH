package com.example.tdahproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MenuFragment extends Fragment {

    View view;
    private Button taskCreate;
    private TextView welcoming;
    private TextView currentTaskName;
    public static TextView currentNextStep;
    public static TextView currentDifficulty;
    public static humain currentUser;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static TextView getCurrentNextStep() {
        return currentNextStep;
    }

    public static TextView getCurrentDifficulty() {
        return currentDifficulty;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_menu, container, false );

        //Recuperation de l'utilisateur actuel
        currentUser = MainActivity.getCurrentUser();

        taskCreate = (Button) view.findViewById(R.id.task_create);
        welcoming = (TextView) view.findViewById(R.id.textViewWelcome);
        currentTaskName = (TextView) view.findViewById(R.id.currentTaskName);
        currentNextStep = (TextView) view.findViewById(R.id.currentNextStep);
        currentDifficulty = (TextView) view.findViewById(R.id.currentTaskDifficulty);

        welcoming.setText("Welcome, " + currentUser.getUsername());
        currentTaskName.setText(currentUser.getObjectifEnCours());

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
