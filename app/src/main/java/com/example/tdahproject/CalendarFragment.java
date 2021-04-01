package com.example.tdahproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarFragment extends Fragment {

    View v;
    private RecyclerView recyclerViewYesterDay;
    private RecyclerView recyclerViewToday;
    private RecyclerView recyclerViewTomorrow;
    private TextView yesterday;
    private TextView today;
    private TextView tomorrow;
    public static ArrayList<Objectif>ListeObjectif = loadingToLobby.getListDesObjectifs();
    private ArrayList<tache> previousTaskList = new ArrayList<tache>();
    private ArrayList<tache> currentTaskList = new ArrayList<tache>();
    private ArrayList<tache> nextTaskList = new ArrayList<tache>();
    Calendar currentDate = Calendar.getInstance();


    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_calendar, container, false );
        String cDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate.getTime());
        String yDate = getYesterdayDateString();
        String tDate = getTomorrowDateString();

        tache manger = new tache("Aller au marcher", "2 heures" , "Manger" );
        //manger.setStatut("IN Progess");
        tache nager = new tache("Aller piscine", "1 heure 30 mn", "Apprendre nager");
        //nager.setStatut("IN Progess");
        tache etudier = new tache("ITI 1200", "3 heures", "etudier");
        //etudier.setStatut("IN Progress");

        previousTaskList.add(manger);

        currentTaskList.add(etudier);

        nextTaskList.add(nager);



        recyclerViewYesterDay = (RecyclerView) view.findViewById(R.id.recyclerViewYesterDay);
        recyclerViewToday = (RecyclerView) view.findViewById(R.id.recyclerViewToday);
        recyclerViewTomorrow = (RecyclerView) view.findViewById(R.id.recyclerViewTomorrow);

        yesterday = (TextView) view.findViewById(R.id.previousDate);
        today = (TextView) view.findViewById(R.id.currentDate);
        tomorrow = (TextView) view.findViewById(R.id.nextDate);

        yesterday.setText(yDate);
        today.setText(cDate);
        tomorrow.setText(tDate);




        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerViewHistoric recyclerAdapterYesterDay = new RecyclerViewHistoric(getContext(),previousTaskList);
        recyclerViewYesterDay.setLayoutManager(layoutManager);
        recyclerViewYesterDay.setAdapter(recyclerAdapterYesterDay);



        RecyclerViewHistoric recyclerAdapterToday = new RecyclerViewHistoric(getContext(),currentTaskList);
        recyclerViewToday.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewToday.setAdapter(recyclerAdapterToday);

/*
        RecyclerViewHistoric recyclerAdapterTomorrow = new RecyclerViewHistoric(getContext(),nextTaskList);
        recyclerViewTomorrow.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTomorrow.setAdapter(recyclerAdapterTomorrow);
*/




        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void previousCompleteTab (ArrayList<tache> listeDeTache, ArrayList<Objectif> listeObjectif){
    for (short i = 0; i < listeObjectif.size(); i++){
        for (short k = 0; i< listeObjectif.get(i).getListeDeTache().size(); k++){
            if (listeObjectif.get(i).getListeDeTache().get(k).getDateFin().equals(getYesterdayDateString()))
                listeDeTache.add(listeObjectif.get(i).getListeDeTache().get(k));
        }
    }
    }

    //Date Getters
    private String getCurrentDateString(){
        Calendar date = Calendar.getInstance();
        String currentDate =  DateFormat.getDateInstance(DateFormat.FULL).toString();

        return currentDate;
    }
    private String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }
    private String getTomorrowDateString() {
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);
        return dateFormat.format(cal.getTime());
    }


}