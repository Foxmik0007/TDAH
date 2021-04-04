package com.volanadesign.tdahproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class taskCreation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

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
    private CheckBox checkBoxDaily;
    private CheckBox checkBoxWeekly;
    private CheckBox checkBoxUnique;
    private SeekBar seekBarImportance;
    private TextView importance;
    private Spinner importanceSpinner;
    private String difficulté;
    private String niveauImportance;
    private String goalType;
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

       // importance = (TextView) findViewById(R.id.lvlOfImportance);
        importanceSpinner = (Spinner) findViewById(R.id.importanceSpinner);

        //CheckBox
        checkBoxEasy = (CheckBox) findViewById(R.id.checkBoxEasy);
        checkBoxMedium = (CheckBox) findViewById(R.id.checkBoxMedium);
        checkBoxHard = (CheckBox) findViewById(R.id.checkBoxHard);
        checkBoxDaily = (CheckBox) findViewById(R.id.checkDaily);
        checkBoxWeekly = (CheckBox) findViewById(R.id.checkWeekly);
        checkBoxUnique = (CheckBox) findViewById(R.id.checkUnique);

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

        checkBoxDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxDaily.isChecked()){
                    checkBoxWeekly.setChecked(false);
                    checkBoxUnique.setChecked(false);
                    goalType = "Daily";
                }
            }
        });

        checkBoxWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxWeekly.isChecked()){
                    checkBoxDaily.setChecked(false);
                    checkBoxUnique.setChecked(false);
                    goalType = "Weekly";
                }
            }
        });

        checkBoxUnique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxUnique.isChecked()){
                    checkBoxDaily.setChecked(false);
                    checkBoxWeekly.setChecked(false);
                    goalType = "Unique";
                }
            }
        });

        //Setup du seekBar d'importance
        /*seekBarImportance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
*/

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.importance, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importanceSpinner.setAdapter(adapter);
        importanceSpinner.setOnItemSelectedListener(this);


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
                nouvelObjectif.setType(goalType);

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

    //Spinner Listener
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String importance = parent.getItemAtPosition(position).toString();
        niveauImportance = importance;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}