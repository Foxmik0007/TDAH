package com.example.tdahproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MessageFragment extends Fragment {



    humain currentUser = MainActivity.getCurrentUser();
    View view;
    private RecyclerView recyclerView;
    private List<Message> messageList;
    RecycleViewMessage recycleViewMessage;

    ArrayList<Message> messageArrayList = new ArrayList<>();
    private Button send;
    EditText messageSend;

    private DatabaseReference messageDatabaseUser;
    private DatabaseReference messageDatabasePartner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        messageDatabaseUser = FirebaseDatabase.getInstance().getReference("Données Utilisateur/" + currentUser.getUsername() + "/Message/" + currentUser.getPartenaire().getUsername());
        messageDatabasePartner = FirebaseDatabase.getInstance().getReference("Données Utilisateur/" + currentUser.getPartenaire().getUsername() + "/Message/" + currentUser.getUsername());
       /* RecycleViewMessage recycleViewMessage = new RecycleViewMessage(getContext(), messageArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recycleViewMessage); */

        //Creation d'humain provisoire.
        /*humain moi = new humain("Charles", "Weird");
        humain lui = new humain("Elisa","Strange");

        messageList = new ArrayList<>();

        Message a = new Message("Hello World", moi, lui);
        Message b = new Message("Salut", lui, moi);

        messageList.add(a);
        messageList.add(b);

         */


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
                messageSend.setText(null);
            }
        });

        // Inflate the layout for this fragment
        return view;

    }


    // Actualisation de la liste à l'Ecran
    public void actualiser (){

        messageList = messageArrayList;
        recycleViewMessage = new RecycleViewMessage(getContext(), messageArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recycleViewMessage);



    }

    //Gere les message
    public void gestionMessage (humain sender, EditText message){

        String dateOfSend = new String();
        Calendar date = Calendar.getInstance();
        dateOfSend = DateFormat.getDateInstance(DateFormat.FULL).format(date.getTime());

        humain proto = new humain("Proto", "QWER");
        Message newMessage = new Message(message.getText().toString().trim(), currentUser, currentUser.getPartenaire());
        messageList.add(newMessage);
        messageDatabaseUser.setValue(messageList);
        messageDatabasePartner.setValue(messageList);

        actualiser();
    }

    @Override
    public void onStart() {
        super.onStart();

        messageDatabaseUser.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Message message = dataSnapshot.getValue(Message.class);
                    Boolean verify = false;

                    for (short i = 0; i < messageArrayList.size(); i++){
                        if (message.getContenu() == messageArrayList.get(i).getContenu())
                            verify = true;
                    }
                    if (!verify)
                        messageArrayList.add(message);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}