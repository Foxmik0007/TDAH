package com.volanadesign.tdahproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarFragment extends Fragment {

    private RecyclerView recyclerViewYesterDay;
    private CalendarView calendarView;
    private TextView dateSelected;
    private String currentWeek;
    public static ArrayList<Objectif>ListeObjectif = loadingToLobby.getListDesObjectifs();
    public ArrayList<Objectif>ListeObjectifDeLaSemaine = new ArrayList<>();
    private ArrayList<tache> goalList = new ArrayList<tache>();
    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_calendar, container, false );

        tache manger = new tache("Aller au marcher", "2 heures" , "Manger" );
        //manger.setStatut("IN Progess");
        tache nager = new tache("Aller piscine", "1 heure 30 mn", "Apprendre nager");
        //nager.setStatut("IN Progess");
        tache etudier = new tache("ITI 1200", "3 heures", "etudier");
        //etudier.setStatut("IN Progress");

         getListDeLaSemaine(ListeObjectif, ListeObjectifDeLaSemaine);
        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        dateSelected = (TextView) view.findViewById(R.id.dateSelected);
        recyclerViewYesterDay = (RecyclerView) view.findViewById(R.id.recycleViewCalendar);

        String dayOfWeek = new String(Long.toString(calendarView.getFirstDayOfWeek()));
        currentWeek = dayOfWeek + " " + getWeek();
        dateSelected.setText(currentWeek);



        RecyclerViewHistoric recyclerAdapterYesterDay = new RecyclerViewHistoric(getContext(), ListeObjectifDeLaSemaine);
        recyclerViewYesterDay.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewYesterDay.setAdapter(recyclerAdapterYesterDay);



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    /*public void previousCompleteTab (ArrayList<tache> listeDeTache, ArrayList<Objectif> listeObjectif){
    for (short i = 0; i < listeObjectif.size(); i++){
        for (short k = 0; i< listeObjectif.get(i).getListeDeTache().size(); k++){
            if (listeObjectif.get(i).getListeDeTache().get(k).getDateFin().equals(getYesterdayDateString()))
                listeDeTache.add(listeObjectif.get(i).getListeDeTache().get(k));
        }
    }
    }*/
/*
    private String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }
    private String getTomorrowDateString() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);
        return dateFormat.format(cal.getTime());
    }
*/

    private Date stringToDate (String date1){

        if(date1==null) return null;

        String patern = new String("dd MMMM yyyy");
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(patern);
        Date stringDate = simpledateformat.parse(date1, pos);
        return stringDate;
    }


    private String getWeek (){

        DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);

        return  dateFormat.format(cal.getTime());
    }

    private String getSevenDays(){
        DateFormat dateFormat = new SimpleDateFormat("DD MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);

        return  dateFormat.format(cal.getTime());
    }

    private void getListDeLaSemaine(ArrayList<Objectif>All, ArrayList<Objectif> listeObjectifFiltree){
        for (short i = 0; i < All.size(); i++){
            if (dateCompare(All.get(i).getDueDate())){
                listeObjectifFiltree.add(All.get(i));
            }
        }
    }

    private Boolean dateCompare(String date1){
        boolean comparaison = false;

        Date date01 = stringToDate(date1);
        Date date02 = stringToDate(getSevenDays());

        if (date02.after(date01)){
            comparaison = true;
        }

        return comparaison;
    }
}