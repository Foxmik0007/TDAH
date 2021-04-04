package com.volanadesign.tdahproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MenuFragment extends Fragment {

    View view;
    private Button taskCreate;
    private TextView welcoming;
    private TextView currentTaskName;
    private TextView currentNextStep;
    private TextView currentDifficulty;
    private TextView currentProgress;
    private TextView currentImportance;
    private TextView currentPartner;
    private TextView currentStartDate;
    private TextView currentDueDate;
    private TextView currentRemainingTime;
    private TextView currentGoalType;
    public static humain currentUser;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);

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
        currentStartDate = (TextView) view.findViewById(R.id.current_start_date);
        currentDueDate = (TextView) view.findViewById(R.id.current_due_date);
        currentRemainingTime = (TextView) view.findViewById(R.id.current_remaining_time);
        currentGoalType = (TextView) view.findViewById(R.id.menu_goal_type);

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

    public void setMenu() {

        welcoming.setText("Welcome, " + currentUser.getUsername());
        currentTaskName.setText(currentUser.getObjectifEnCours());

        Calendar calendar = Calendar.getInstance();
        String date = DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime());

        for (short i = 0; i < loadingToLobby.getListDesObjectifs().size(); i++) {
            if (loadingToLobby.getListDesObjectifs().get(i).getNom().equals(currentUser.getObjectifEnCours())) {
                currentNextStep.setText(loadingToLobby.getListDesObjectifs().get(i).getNextStep().getNom());
                currentDifficulty.setText(loadingToLobby.getListDesObjectifs().get(i).getDifficulté());
                currentImportance.setText(loadingToLobby.getListDesObjectifs().get(i).getImportance());
                currentPartner.setText(loadingToLobby.getListDesObjectifs().get(i).getPartner().getName());
                currentStartDate.setText(loadingToLobby.getListDesObjectifs().get(i).getDateDeCreation());
                currentDueDate.setText(loadingToLobby.getListDesObjectifs().get(i).getDueDate());
                currentGoalType.setText(loadingToLobby.getListDesObjectifs().get(i).getType() + " Goal");

                getDaysDifference(date, loadingToLobby.getListDesObjectifs().get(i).getDueDate());

                if (loadingToLobby.getListDesObjectifs().get(i).getProgression() > 98) {
                    currentProgress.setText("FINISHED");
                } else {
                    currentProgress.setText(loadingToLobby.getListDesObjectifs().get(i).getProgression() + "%");
                }


            }
        }
    }

    //Calcul des jours restants
    public void getDaysDifference(String fromDate, String toDate) {
        try {
            Date date1;
            Date date2;
            SimpleDateFormat dates = new SimpleDateFormat("dd MMMM yyyy");
            date1 = dates.parse(fromDate);
            date2 = dates.parse(toDate);
            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);
            String dayDifference = Long.toString(differenceDates);
            currentRemainingTime.setText(dayDifference + " days");
        } catch (Exception exception) {
            currentRemainingTime.setText("Error");
        }

    }

}

