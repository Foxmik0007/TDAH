package com.example.tdahproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {


    humain currentUser = MainActivity.getCurrentUser();
    View view;
    private RecyclerView recyclerView;
    private List<Message> messageList;

    private Button send;
    EditText messageSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //Creation d'humain provisoire.
        humain moi = new humain("Charles", "Weird");
        humain lui = new humain("Elisa","Strange");

        messageList = new ArrayList<>();

        Message a = new Message("Hello World", moi, lui);
        Message b = new Message("Salut", lui, moi);

        messageList.add(a);
        messageList.add(b);

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
    }

    public void actualiser (){
        RecycleViewMessage recycleViewMessage = new RecycleViewMessage(getApplicationContext(), messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
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