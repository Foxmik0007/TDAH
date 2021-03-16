package com.example.tdahproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class taskCreation extends AppCompatActivity {

    public static humain currentUser;
    public static ArrayList <Objectif> listeDesObjectifs = loadingToLobby.getListDesObjectifs();

    private EditText nomDeObjectif;
    private EditText nomDeTache;
    private EditText dureeDeTache;
    private String dateDeCreation;
    private Button confirmerCreation;
    private Button ajouterTache;
    private CheckBox checkBoxEasy;
    private CheckBox checkBoxMedium;
    private CheckBox checkBoxHard;
    private SeekBar seekBarImportance;
    private TextView importance;
    private String difficulté;
    private String niveauImportance;
    private ArrayList<tache> listeDeNouvelleTache;
    private humain nouveauPartenaire;
    private Message message;

    DatabaseReference GOALDATABASE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        //Recuperation de l'utilisateur actuel
        currentUser = MainActivity.getCurrentUser();

        //Configuration de la base de données
        //Creer un path personnel pour chaque utilisateur mais necessite d'abord l'acces au données de l'utilisateur
        GOALDATABASE = FirebaseDatabase.getInstance().getReference( "Données Utilisateur/" + currentUser.getUsername()  + "/Liste des Objectifs");

        //Button
        confirmerCreation = (Button) findViewById(R.id.confirmGoalCreation);
        ajouterTache = (Button) findViewById(R.id.buttonAddStep);

        //EditText and Textview
        nomDeObjectif = (EditText) findViewById(R.id.goalName_create);
        nomDeTache = (EditText) findViewById(R.id.stepName_creation);
        dureeDeTache = (EditText) findViewById(R.id.durationCreation);

        importance = (TextView) findViewById(R.id.lvlOfImportance);

        //CheckBox
        checkBoxEasy = (CheckBox) findViewById(R.id.checkBoxEasy);
        checkBoxMedium = (CheckBox) findViewById(R.id.checkBoxMedium);
        checkBoxHard = (CheckBox) findViewById(R.id.checkBoxHard);

        //SeekBar
        seekBarImportance = (SeekBar) findViewById(R.id.seekBarImportance);

        //Recuperation de la date d'aujoud'hui
        Calendar calendar = Calendar.getInstance();
        dateDeCreation = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        listeDeNouvelleTache = new ArrayList<>();

        //Button d'ajout des tache
        ajouterTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creer un tableau de tache et utiliser les setters si ne marche pas
                tache nouvelleTache = new tache(nomDeTache.getText().toString().trim(), dureeDeTache.getText().toString().trim());
               listeDeNouvelleTache.add(nouvelleTache);

                nomDeTache.setText(null);
                dureeDeTache.setText(null);

                Toast.makeText(taskCreation.this, "Task added, Enter a new Task ", Toast.LENGTH_SHORT).show();
            }
        });

        //Setup des CheckBox
        checkBoxEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxEasy.isChecked())
                    difficulté = "Easy";
            }
        });
        checkBoxMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxMedium.isChecked())
                    difficulté = "Medium";
            }
        });
        checkBoxHard.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                if(checkBoxHard.isChecked())
                    difficulté = "Hard";
            }
        });

        //Setup du seekBar d'importance
        seekBarImportance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 25){
                    niveauImportance = "Pas important";
                    importance.setText(niveauImportance);
                }
                if ( progress < 50 && progress > 25){
                    niveauImportance = "Peu important";
                    importance.setText(niveauImportance);
                }
                if ( progress < 75 && progress > 50){
                    niveauImportance = "Assez important";
                    importance.setText(niveauImportance);
                }
                if ( progress < 100 && progress > 75){
                    niveauImportance = "Très important";
                    importance.setText(niveauImportance);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Mise en place de la messagerie
        message = new Message("No message", currentUser, null);

        //Button de Confirmation de la creation
        confirmerCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objectif nouvelObjectif = new Objectif();


                nouvelObjectif.setNom(nomDeObjectif.getText().toString().trim());
                nouvelObjectif.setDateDeCreation(dateDeCreation);
                nouvelObjectif.setDifficulté(difficulté);
                nouvelObjectif.setImportance(niveauImportance);
                nouvelObjectif.setListeDeTache(listeDeNouvelleTache);
                nouvelObjectif.setProgression(0);
                nouvelObjectif.setEtat("IN PROCESS");
                nouvelObjectif.setMessagerie(message);

                //Mise à jour de la base de donnée en ligne
                GOALDATABASE.child(nouvelObjectif.getNom()).setValue(nouvelObjectif);
                Toast.makeText(taskCreation.this, "Objectif ajoutée", Toast.LENGTH_SHORT).show();

                //Mise à jour de la base de données locale


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
}