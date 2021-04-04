package com.volanadesign.tdahproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerViewContact extends RecyclerView.Adapter<RecyclerViewContact.MyViewHolder> {

    Context context;
    List<humain>contactList;
    DatabaseReference PARTNERDATABASE;
    humain currentUser = MainActivity.getCurrentUser();

    public RecyclerViewContact(Context context, List<humain> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerViewContact.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;

        v = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }


    //A modifier plus tard
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewContact.MyViewHolder holder, int position) {
       holder.contactId.setText("M");
       holder.contactLastMessage.setText(contactList.get(position).getEmail());
       holder.contactName.setText(contactList.get(position).getUsername());
       PARTNERDATABASE = FirebaseDatabase.getInstance().getReference("UserInformation/" + currentUser.getUsername() +"/partenaire");

       holder.select.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               currentUser.setPartenaire(contactList.get(position));
               //PARTNERDATABASE.setValue(contactList.get(position));
               taskCreation.setNouveauPartenaire(contactList.get(position));
               Toast.makeText(context, "Partenaire Selected", Toast.LENGTH_SHORT).show();
               ((Activity)context).finish();
           }
       });

    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView contactId;
        private TextView contactName;
        private TextView contactLastMessage;
        private Button select;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            contactId = (TextView) itemView.findViewById(R.id.contact_identity);
            contactName = (TextView) itemView.findViewById(R.id.contact_name);
            contactLastMessage = (TextView) itemView.findViewById(R.id.contact_message);
            select = (Button) itemView.findViewById(R.id.confirmPartner);

        }
    }
}
