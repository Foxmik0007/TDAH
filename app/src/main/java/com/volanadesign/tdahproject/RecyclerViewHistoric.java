package com.volanadesign.tdahproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewHistoric extends RecyclerView.Adapter<RecyclerViewHistoric.MyViewHolder>{

    Context mContext;
    static List<Objectif> ListeDesTaches;

    public RecyclerViewHistoric(Context mContext, List<Objectif> mData) {
        this.mContext = mContext;
        this.ListeDesTaches = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_historic, parent, false);

        MyViewHolder vHolder = new RecyclerViewHistoric.MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.taskName.setText(ListeDesTaches.get(position).getNom());
        holder.objectiveDifficulty.setText(ListeDesTaches.get(position).getEtat());

        if (ListeDesTaches.get(position).getEtat().equals("FINISHED")){
            holder.state.setCardBackgroundColor(Color.parseColor("#95f060"));
        } else {
            holder.state.setCardBackgroundColor(Color.parseColor("#f0e460"));
        }

    }


    @Override
    public int getItemCount() {
        return ListeDesTaches.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView taskName;
        private TextView objectiveDifficulty;
        private CardView state;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            taskName = (TextView) itemView.findViewById(R.id.historic_task_name);
            objectiveDifficulty = (TextView) itemView.findViewById(R.id.historic_objectif_difficulty);
            state = (CardView) itemView.findViewById(R.id.state);


        }
    }

}
