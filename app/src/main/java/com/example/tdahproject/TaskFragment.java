package com.example.tdahproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private ArrayList<Objectif> taskList = loadingToLobby.getListDesObjectifs();

    public TaskFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate( R.layout.fragment_task, container, false );
        recyclerView = (RecyclerView) v.findViewById(R.id.task_recycleView);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}