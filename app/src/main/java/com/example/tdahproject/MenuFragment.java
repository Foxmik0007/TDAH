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
    public TextView currentNextStep;
    public TextView currentDifficulty;
    public TextView currentProgress;
    public TextView currentImportance;
    public TextView currentPartner;
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
        currentTaskName = (TextView) view.findViewById(R.id.currentTaskName);
        currentNextStep = (TextView) view.findViewById(R.id.currentNextStep);
        currentDifficulty = (TextView) view.findViewById(R.id.currentTaskDifficulty);
        currentProgress = (TextView) view.findViewById(R.id.menu_progress);
        currentImportance = (TextView) view.findViewById(R.id.current_task_importance);
        currentPartner = (TextView) view.findViewById(R.id.current_partner_name);


        setMenu();

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

    public void setMenu (){
        welcoming.setText("Welcome, " + currentUser.getUsername());
        currentTaskName.setText(currentUser.getObjectifEnCours());
        for (short i = 0; i < loadingToLobby.getListDesObjectifs().size(); i++){
            if (loadingToLobby.getListDesObjectifs().get(i).getNom().equals(currentUser.getObjectifEnCours())){
                currentNextStep.setText(loadingToLobby.getListDesObjectifs().get(i).getNextStep().getNom());
                currentDifficulty.setText(loadingToLobby.getListDesObjectifs().get(i).getDifficulté());
                currentImportance.setText(loadingToLobby.getListDesObjectifs().get(i).getImportance());
                currentPartner.setText(loadingToLobby.getListDesObjectifs().get(i).getPartner().getName());
                if (loadingToLobby.getListDesObjectifs().get(i).getProgression() > 98){
                    currentProgress.setText("FINISHED");
                }else
                {
                    currentProgress.setText(loadingToLobby.getListDesObjectifs().get(i).getProgression() + "%");
                }

            }
        }
    }


    }
