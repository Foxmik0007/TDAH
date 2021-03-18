package com.example.tdahproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MessageFragment extends Fragment {



    humain currentUser = MainActivity.getCurrentUser();
    View view;
    private RecyclerView recyclerView;
    private List<Message> messageList;

    private Button send;
    EditText messageSend;

    private DatabaseReference messageDatabase;

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

        send = (Button) view.findViewById(R.id.messagesend);
        messageSend = (EditText) view.findViewById(R.id.editNewMessage);

        //Formation de la liste
        actualiser();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gestionMessage(currentUser, messageSend);
            }
        });

        // Inflate the layout for this fragment
        return view;

    }


    // Actualisation de la liste à l'Ecran
    public void actualiser (){
        RecycleViewMessage recycleViewMessage = new RecycleViewMessage(getContext(), messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recycleViewMessage);

    }

    //Gere les message
    public void gestionMessage (humain sender, EditText message){
        humain proto = new humain("Proto", "QWER");
        Message newMessage = new Message(message.getText().toString().trim(), sender, proto);
        messageList.add(newMessage);

        actualiser();
    }
}