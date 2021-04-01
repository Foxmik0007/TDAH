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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {



    Context mContext;
    static List<Objectif> ListeDesObjectifs;
    DatabaseReference taskUpdateDatabase;
    DatabaseReference userUpdateDatabase;
    DatabaseReference XPUpdateDatabase;
    humain currentUser = MainActivity.getCurrentUser();
    public static Objectif currentGoal = new Objectif();
    Calendar date = Calendar.getInstance();
    String finishDate = DateFormat.getDateInstance(DateFormat.FULL).format(date.getTime());

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
        userUpdateDatabase = FirebaseDatabase.getInstance().getReference("UserInformation/" + currentUser.getUsername() + "/objectifEnCours");
        XPUpdateDatabase = FirebaseDatabase.getInstance().getReference("UserInformation");


            //Gestion du code couleur
            if (ListeDesObjectifs.get(position).getDifficulté().equals("Hard"))
                holder.task_name.setBackgroundColor(Color.RED);

            if (ListeDesObjectifs.get(position).getDifficulté().equals("Medium"))
                holder.task_name.setBackgroundColor(Color.parseColor("#ebf705"));

            if (ListeDesObjectifs.get(position).getDifficulté().equals("Easy"))
                holder.task_name.setBackgroundColor(Color.GREEN);

            holder.task_name.setText(ListeDesObjectifs.get(position).getNom());
            //holder.task_diffiulty.setText(ListeDesObjectifs.get(position).getDifficulté());
            //holder.imageView.setImageResource(ListeDesObjectifs.get(position).getPicture());
            holder.task_steps.setText("Next step : " + ListeDesObjectifs.get(position).getNextStep().getNom());

            holder.progressBar.setProgress(calculProgress(ListeDesObjectifs.get(position)));

            //holder.task_diffiulty.setText(calculProgress(ListeDesObjectifs.get(position)));


        if (holder.task_steps.getText().equals("Next step : " + null)){
            holder.task_steps.setText("Congrats, You reach your Goal !!!");
            ListeDesObjectifs.get(position).setNextStep(null);
            //ListeDesObjectifs.remove(ListeDesObjectifs.get(position));
        }



        //Validation d'une tache
        holder.validerTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                calculXP(currentUser, ListeDesObjectifs.get(position), v);


                //Mise à jour des données
                ListeDesObjectifs.get(position).getNextStep().setDateFin(finishDate);
                ListeDesObjectifs.get(position).getNextStep().setStatut("Complete");


                //Verification de l'accomplissement de l'objectif
                if (calculProgress(ListeDesObjectifs.get(position)) == 100){
                    ListeDesObjectifs.get(position).setEtat("FINISHED");
                    holder.task_steps.setText("Congrats, You reach your Goal !!!");
                        //ListeDesObjectifs.remove(ListeDesObjectifs.get(position));

                }



                //Mise à jour de ce qu'on voit à l'ecran
                holder.progressBar.setProgress(calculProgress(ListeDesObjectifs.get(position)));
                ListeDesObjectifs.get(position).setProgression(calculProgress(ListeDesObjectifs.get(position)));

                if (!ListeDesObjectifs.get(position).getEtat().equals("FINISHED") ){
                    holder.task_steps.setText("Next step : " + ListeDesObjectifs.get(position).getNextStep().getNom());
                }

                //Mise à jour de la base de donnée
                XPUpdateDatabase.child(currentUser.getUsername()).setValue(currentUser);
                taskUpdateDatabase.child(ListeDesObjectifs.get(position).getNom()).setValue(ListeDesObjectifs.get(position));
            }
        });

        //Selection du main Goal
        holder.selectGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.setObjectifEnCours(ListeDesObjectifs.get(position).getNom());
                currentUser.setPartenaire(ListeDesObjectifs.get(position).getPartner());
                userUpdateDatabase.setValue(ListeDesObjectifs.get(position).getNom());
                Toast.makeText(mContext, ListeDesObjectifs.get(position).getNom() + " is now the main task", Toast.LENGTH_SHORT).show();
            }
        });

        //Fermer une tache
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskUpdateDatabase.child(ListeDesObjectifs.get(position).getNom()).setValue(null);
                ListeDesObjectifs.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), ListeDesObjectifs.size());


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
        private Drawable color;
        private Button close;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            task_name = (TextView) itemView.findViewById(R.id.task_name);
            task_diffiulty = (TextView) itemView.findViewById(R.id.task_difficulty);
            task_steps = (TextView) itemView.findViewById(R.id.task_steps);
            progressBar = (ProgressBar) itemView.findViewById(R.id.task_progressBar);
            validerTache = (Button) itemView.findViewById(R.id.validerTache);
            selectGoal = (Button) itemView.findViewById(R.id.selectObjective);
            close = (Button) itemView.findViewById(R.id.task_close);
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

    //Gestion des points de recompenses
    public  void calculXP (humain personne, Objectif objectif, View view){
        if (objectif.getDifficulté().equals("Easy")){
            personne.setExperience( personne.getExperience() + 2);
        }

        if (objectif.getDifficulté().equals("Medium")){
            personne.setExperience(personne.getExperience() +5);
        }

        if (objectif.getDifficulté().equals("Hard")){
            personne.setExperience(personne.getExperience() + 10);
        }

        if (personne.getExperience() >= 100){
            personne.setNiveau(personne.getNiveau() + 1);
            personne.setExperience(personne.getExperience()-100);
            Toast.makeText(view.getContext(), "Congrats, Level UP", Toast.LENGTH_SHORT).show();
        }
    }

    public static Objectif getCurrentGoal() {
        return currentGoal;
    }
}
