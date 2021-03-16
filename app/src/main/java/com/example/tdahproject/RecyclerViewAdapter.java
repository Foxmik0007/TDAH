package com.example.tdahproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {



    Context mContext;
    static List<Objectif> ListeDesObjectifs;
    DatabaseReference taskUpdateDatabase;
    DatabaseReference userUpdateDatabase;
    humain currentUser = MainActivity.getCurrentUser();

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


        //Gestion des tache completer à faire



        taskUpdateDatabase = FirebaseDatabase.getInstance().getReference("Données Utilisateur/" + currentUser.getUsername() + "/Liste des Objectifs");
        userUpdateDatabase = FirebaseDatabase.getInstance().getReference("Données Utilisateur/" + currentUser.getUsername() + "/Main Task");

            //Gestion du code couleur
            if (ListeDesObjectifs.get(position).getDifficulté().equals("Hard"))
                holder.task_name.setBackgroundColor(Color.RED);

            if (ListeDesObjectifs.get(position).getDifficulté().equals("Medium"))
                holder.task_name.setBackgroundColor(Color.parseColor("#f2f593"));

            if (ListeDesObjectifs.get(position).getDifficulté().equals("Easy"))
                holder.task_name.setBackgroundColor(Color.GREEN);

            holder.task_name.setText(ListeDesObjectifs.get(position).getNom());
            //holder.task_diffiulty.setText(ListeDesObjectifs.get(position).getDifficulté());
            //holder.imageView.setImageResource(ListeDesObjectifs.get(position).getPicture());
            holder.task_steps.setText("Next: " + ListeDesObjectifs.get(position).getNextStep().getNom());

            holder.progressBar.setProgress(calculProgress(ListeDesObjectifs.get(position)));

            //holder.task_diffiulty.setText(calculProgress(ListeDesObjectifs.get(position)));





        //Ajout du support de la base de données plus tard
        holder.validerTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mise à jour des données
                ListeDesObjectifs.get(position).getNextStep().setStatut("Complete");

                //Verification de l'accomplissement de l'objectif
                if (calculProgress(ListeDesObjectifs.get(position)) == 100){
                    ListeDesObjectifs.get(position).setEtat("FINISHED");
                }

                //Mise à jour de la base de donnée
                taskUpdateDatabase.child(ListeDesObjectifs.get(position).getNom()).setValue(ListeDesObjectifs.get(position));

                //Mise à jour de ce qu'on voit à l'ecran
                holder.progressBar.setProgress(calculProgress(ListeDesObjectifs.get(position)));


                if (!ListeDesObjectifs.get(position).getEtat().equals("FINISHED") ){
                    holder.task_steps.setText("Next step : " + ListeDesObjectifs.get(position).getNextStep().getNom());
                } else {
                    holder.task_steps.setText("Congrats, You reach your Goal !!!");
                    ListeDesObjectifs.remove(ListeDesObjectifs.get(position));
                }

            }
        });

        holder.selectGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.setObjectifEnCours(ListeDesObjectifs.get(position));
                userUpdateDatabase.setValue(ListeDesObjectifs.get(position));
            }
        });
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
        private Button validerTache;
        private Button selectGoal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            task_name = (TextView) itemView.findViewById(R.id.task_name);
            task_diffiulty = (TextView) itemView.findViewById(R.id.task_difficulty);
            task_steps = (TextView) itemView.findViewById(R.id.task_steps);
            progressBar = (ProgressBar) itemView.findViewById(R.id.task_progressBar);
            validerTache = (Button) itemView.findViewById(R.id.validerTache);
            selectGoal = (Button) itemView.findViewById(R.id.selectObjective);


        }


    }


    //Calcul de la progression de la tache
    public int calculProgress (Objectif unObjectif){
        int currentProgress = 0;
        int ordreDeProgression = 100 / unObjectif.getListeDeTache().size();
        for (short i = 0; i < unObjectif.getListeDeTache().size(); i++){
            if (unObjectif.getListeDeTache().get(i).getStatut().equals("In progress"))
                currentProgress = currentProgress + 0;
            else
                currentProgress = currentProgress + ordreDeProgression;
        }
        return currentProgress;
    }

}
