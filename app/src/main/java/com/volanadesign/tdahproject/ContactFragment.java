package com.volanadesign.tdahproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ContactFragment extends Fragment {


    humain currentUser = MainActivity.getCurrentUser();
    View view;
    private RecyclerView recyclerView;
    private List<humain> contactList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        humain moi = new humain("Charles", "Weird");
        humain lui = new humain("Elisa","Strange");

        contactList = new ArrayList<>();

        Message a = new Message("Hello World", moi, lui);
        Message b = new Message("Salut", lui, moi);

        contactList.add(a);
        contactList.add(b);

        RecyclerViewContact recyclerViewContact = new RecyclerViewContact(getContext(), contactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewContact);
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.contact_recycleView);
        actualiser();
        // Inflate the layout for this fragment
        return view;
    }

    public void actualiser (){


    }
}