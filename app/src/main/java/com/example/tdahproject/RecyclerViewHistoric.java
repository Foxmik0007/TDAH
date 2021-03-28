package com.example.tdahproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewHistoric extends RecyclerView.Adapter<RecyclerViewHistoric.MyViewHolder>{

    Context mContext;
    static List<tache> ListeDesTaches;

    public RecyclerViewHistoric(Context mContext, List<tache> mData) {
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
        holder.objectiveName.setText(ListeDesTaches.get(position).getNomObjectif());
        holder.objectiveDifficulty.setText(ListeDesTaches.get(position).getStatut());
        holder.taskState.setText("Test");
    }


    @Override
    public int getItemCount() {
        return ListeDesTaches.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView taskName;
        private TextView objectiveName;
        private TextView objectiveDifficulty;
        private TextView taskState;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            taskName = (TextView) itemView.findViewById(R.id.historic_task_name);
            objectiveName = (TextView) itemView.findViewById(R.id.historic_objective_name);
            objectiveDifficulty = (TextView) itemView.findViewById(R.id.historic_objectif_difficulty);
            taskState = (TextView) itemView.findViewById(R.id.historic_task_state);


        }
    }

}
