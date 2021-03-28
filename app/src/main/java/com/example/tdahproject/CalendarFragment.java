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
    public static ArrayList<tache> previousTaskList = new ArrayList<tache>();
    public static ArrayList<tache> currentTaskList = new ArrayList<tache>();
    public static ArrayList<tache> nextTaskList = new ArrayList<tache>();
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
        previousTaskList.add(nager);
        previousTaskList.add(etudier);

        currentTaskList.add(manger);
        currentTaskList.add(nager);
        currentTaskList.add(etudier);

        nextTaskList.add(manger);
        nextTaskList.add(nager);
        nextTaskList.add(etudier);


        recyclerViewYesterDay = (RecyclerView) view.findViewById(R.id.recyclerViewYesterDay);
        recyclerViewToday = (RecyclerView) view.findViewById(R.id.recyclerViewToday);
        recyclerViewTomorrow = (RecyclerView) view.findViewById(R.id.recyclerViewTomorrow);

        yesterday = (TextView) view.findViewById(R.id.previousDate);
        today = (TextView) view.findViewById(R.id.currentDate);
        tomorrow = (TextView) view.findViewById(R.id.nextDate);

        yesterday.setText(yDate);
        today.setText(cDate);
        tomorrow.setText(tDate);

        RecyclerViewHistoric recyclerAdapterYesterDay = new RecyclerViewHistoric(getContext(),previousTaskList);
        RecyclerViewHistoric recyclerAdapterToday = new RecyclerViewHistoric(getContext(),currentTaskList);
        RecyclerViewHistoric recyclerAdapterTomorrow = new RecyclerViewHistoric(getContext(),nextTaskList);

        recyclerViewYesterDay.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewYesterDay.setAdapter(recyclerAdapterYesterDay);

        recyclerViewToday.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewToday.setAdapter(recyclerAdapterToday);

        recyclerViewTomorrow.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTomorrow.setAdapter(recyclerAdapterTomorrow);



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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