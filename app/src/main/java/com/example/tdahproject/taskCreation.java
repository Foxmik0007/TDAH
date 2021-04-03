package com.example.tdahproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
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

public class taskCreation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static humain currentUser;
    public static ArrayList <Objectif> listeDesObjectifs = loadingToLobby.getListDesObjectifs();

    private EditText nomDeObjectif;
    private EditText nomDeTache;
    private EditText dureeDeTache;
    private String dateDeCreation;
    private String dueDate;
    private Button confirmerCreation;
    private Button ajouterTache;
    private Button choosePartner;
    private Button dueDateSelect;
    private CheckBox checkBoxEasy;
    private CheckBox checkBoxMedium;
    private CheckBox checkBoxHard;
    private SeekBar seekBarImportance;
    private TextView importance;
    private String difficulté;
    private String niveauImportance;
    private ArrayList<tache> listeDeNouvelleTache;
    public static humain nouveauPartenaire;
    private Message message;
    private Objectif nouvelObjectif = new Objectif();

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
        choosePartner = (Button) findViewById(R.id.buttonChoosePartner);
        dueDateSelect = (Button) findViewById(R.id.btn_due_date_select);

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
        dateDeCreation = DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime());

        listeDeNouvelleTache = new ArrayList<>();

        //Button d'ajout des tache
        ajouterTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creer un tableau de tache et utiliser les setters si ne marche pas
                tache nouvelleTache = new tache(nomDeTache.getText().toString().trim(), dureeDeTache.getText().toString().trim(), nomDeObjectif.getText().toString());
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
                if(checkBoxEasy.isChecked()){
                    checkBoxMedium.setChecked(false);
                    checkBoxHard.setChecked(false);
                    difficulté = "Easy";
                }
            }
        });
        checkBoxMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxMedium.isChecked()){
                    checkBoxEasy.setChecked(false);
                    checkBoxHard.setChecked(false);
                    difficulté = "Medium";
                }
            }
        });
        checkBoxHard.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                if(checkBoxHard.isChecked()){
                    checkBoxEasy.setChecked(false);
                    checkBoxMedium.setChecked(false);
                    difficulté = "Hard";
                }
            }
        });

        //Setup du seekBar d'importance
        seekBarImportance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 25){
                    niveauImportance = "Not Important";
                    importance.setText(niveauImportance);
                }
                if ( progress < 50 && progress > 25){
                    niveauImportance = "Not Very Important";
                    importance.setText(niveauImportance);
                }
                if ( progress < 75 && progress > 50){
                    niveauImportance = "Important";
                    importance.setText(niveauImportance);
                }
                if ( progress < 100 && progress > 75){
                    niveauImportance = "Very Important";
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

        //Button settings du partenaire
        choosePartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), partnerSelect.class);
                startActivity(intent);
            }
        });

        dueDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "Due Date");
            }
        });

        //Mise en place de la messagerie
        message = new Message();

        //Button de Confirmation de la creation
        confirmerCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nouvelObjectif.setNom(nomDeObjectif.getText().toString().trim());
                nouvelObjectif.setDateDeCreation(dateDeCreation);
                nouvelObjectif.setDueDate(dueDate);
                nouvelObjectif.setDifficulté(difficulté);
                nouvelObjectif.setImportance(niveauImportance);
                nouvelObjectif.setListeDeTache(listeDeNouvelleTache);
                nouvelObjectif.setProgression(0);
                nouvelObjectif.setEtat("IN PROCESS");
                nouvelObjectif.setMessagerie(message);
                nouvelObjectif.setPartner(nouveauPartenaire);

                //Mise à jour de la base de donnée en ligne
                GOALDATABASE.child(nouvelObjectif.getNom()).setValue(nouvelObjectif);
                Toast.makeText(taskCreation.this, "Objectif ajoutée", Toast.LENGTH_SHORT).show();

                //Mise à jour de la base de données locale

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dueDate = DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime());

    }

    public static void setNouveauPartenaire(humain nouveauPartenaire) {
        taskCreation.nouveauPartenaire = nouveauPartenaire;
    }
}