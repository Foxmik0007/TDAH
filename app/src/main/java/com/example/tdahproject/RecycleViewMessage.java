package com.example.tdahproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewMessage extends RecyclerView.Adapter<RecycleViewMessage.MyViewHolder> {

    Context context;
    List<Message> messageList;


    public RecycleViewMessage(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;

        v = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewMessage.MyViewHolder holder, int position) {
        holder.messageContent.setText(messageList.get(position).getContenu());
        holder.messageSender.setText(messageList.get(position).getEmetteur().getUsername());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView messageContent;
        private TextView messageSender;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            messageContent = (TextView) itemView.findViewById(R.id.message_Content);
            messageSender = (TextView) itemView.findViewById(R.id.sender_name);

        }
    }
}
