package com.example.tdahproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavController myController;
    public static humain currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //Parametrisation de la barre de tache
        bottomNavigationView = (BottomNavigationView) findViewById( R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        currentUser = Login.getCurrentUser();

        //Selection de Menufragment comme fragment d'accueil
        if(savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new MenuFragment()).commit();
         }
    }

    //Manipulation de la barre de tache
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragmentSelected = null;

                    switch (item.getItemId()){
                        case R.id.menuFragment:
                            fragmentSelected = new MenuFragment();
                            break;
                        case R.id.taskFragment:
                            fragmentSelected = new TaskFragment();
                            break;
                        case R.id.messageFragment:
                            fragmentSelected = new MessageFragment();
                            break;
                        case R.id.calendarFragment:
                            fragmentSelected = new CalendarFragment();
                            break;
                        case R.id.blockingFragment:
                            fragmentSelected = new BlockingFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragmentSelected).commit();

                    return true;
                }
            };

    public static humain getCurrentUser() {
        return currentUser;
    }

}