package com.example.tdahproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    Context mContext;
    List<Objectif> ListeDesObjectifs;

    public RecyclerViewAdapter(Context mContext, List<Objectif> mData) {
        this.mContext = mContext;
        this.ListeDesObjectifs = mData;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_task, parent, false);
        MyViewHolder vHolder = new MyViewHolder (v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        //Gestion du code couleur
        if (ListeDesObjectifs.get(position).getDifficulté().equals("Hard"))
            holder.task_name.setBackgroundColor(Color.RED);

        if (ListeDesObjectifs.get(position).getDifficulté().equals("Medium"))
            holder.task_name.setBackgroundColor(Color.parseColor("#f2f593"));

        if (ListeDesObjectifs.get(position).getDifficulté().equals("Easy"))
            holder.task_name.setBackgroundColor(Color.GREEN);

        holder.task_name.setText(ListeDesObjectifs.get(position).getNom());
        holder.task_diffiulty.setText(ListeDesObjectifs.get(position).getDifficulté());
        //holder.imageView.setImageResource(ListeDesObjectifs.get(position).getPicture());
        holder.task_steps.setText("Next step : " + ListeDesObjectifs.get(position).getNextStep());
        holder.progressBar.setProgress(37);


    }

    @Override
    public int getItemCount() {
        return ListeDesObjectifs.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView task_name;
        private TextView task_diffiulty;
        private TextView task_steps;
        private ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            task_name = (TextView) itemView.findViewById(R.id.task_name);
            task_diffiulty = (TextView) itemView.findViewById(R.id.task_difficulty);
            task_steps = (TextView) itemView.findViewById(R.id.task_steps);
            progressBar = (ProgressBar) itemView.findViewById(R.id.task_progressBar);
        }
    }
}
