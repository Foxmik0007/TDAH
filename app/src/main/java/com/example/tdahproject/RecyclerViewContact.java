package com.example.tdahproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.dynamic.IFragmentWrapper;

import java.util.List;

public class RecyclerViewContact extends RecyclerView.Adapter<RecyclerViewContact.MyViewHolder> {

    Context context;
    List<Message>contactList;

    public RecyclerViewContact(Context context, List<Message> contactList) {
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
       holder.contactLastMessage.setText(contactList.get(position).getContenu());
       holder.contactName.setText(contactList.get(position).getEmetteur().getUsername());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            contactId = (TextView) itemView.findViewById(R.id.contact_identity);
            contactName = (TextView) itemView.findViewById(R.id.contact_name);
            contactLastMessage = (TextView) itemView.findViewById(R.id.contact_message);

        }
    }
}
