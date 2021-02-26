package com.example.tdahproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MessageFragment extends Fragment {


    View view;
    private RecyclerView recyclerView;
    private List<Message> messageList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


        //Creation d'humain provisoire.
        humain moi = new humain("Charles", "Weird");
        humain lui = new humain("Elisa","Strange");

        messageList = new ArrayList<>();

        Message a = new Message("Hello World", moi, lui);
        Message b = new Message("Salut", lui, moi);

        messageList.add(a);
        messageList.add(b);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate( R.layout.fragment_message, container, false );
        recyclerView = (RecyclerView) view.findViewById(R.id.message_recyclerView);
        RecycleViewMessage recycleViewMessage = new RecycleViewMessage(getContext(), messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recycleViewMessage);

        // Inflate the layout for this fragment
        return view;

    }
}