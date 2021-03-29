package com.example.tdahproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlockingFragment extends Fragment {


    private TextView profileName;
    private TextView profileUserName;
    private  TextView profileMail;
    private TextView profileBirthDay;
    private TextView level;
    private TextView experience;
    public static humain currentUser = MainActivity.getCurrentUser();

    public BlockingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_blocking, container, false );

        profileName = (TextView) view.findViewById(R.id.profile_name);
        profileUserName = (TextView) view.findViewById(R.id.profile_username);
        profileMail = (TextView) view.findViewById(R.id.profile_email);
        profileBirthDay = (TextView) view.findViewById(R.id.profile_birthday);
        level = (TextView) view.findViewById(R.id.profile_level);
        experience = (TextView) view.findViewById(R.id.profile_xp);

        profileName.setText(currentUser.getName());
        profileUserName.setText(currentUser.getUsername());
        profileMail.setText(currentUser.getEmail());
        profileBirthDay.setText(currentUser.getBirthday());
        level.setText(Integer.toString(currentUser.getNiveau()));
        experience.setText(Integer.toString(currentUser.getExperience())) ;

        // Inflate the layout for this fragment
        return view;
    }
}