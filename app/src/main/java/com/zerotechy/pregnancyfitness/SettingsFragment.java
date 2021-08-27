package com.zerotechy.pregnancyfitness;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment{
    ImageView privacy,reminder,rate,about,difficulty;
    RadioGroup radioGroup;
    RadioButton radioButton;
    SharedPreferences sharedPreferences;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_settings, container, false);
        difficulty=(ImageView)v.findViewById(R.id.difficultybtn);
        reminder=(ImageView)v.findViewById(R.id.reminderbtn);
        rate=(ImageView)v.findViewById(R.id.ratingbtn);
        about=(ImageView)v.findViewById(R.id.aboutbtn);
        privacy=(ImageView) v.findViewById(R.id.privacybtn);



        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getContext(),About.class));
            }
        });




            rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String appPackageName = getActivity().getPackageName();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            });
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Reminder.class));


            }
        });

        difficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty();
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Policyview.class));
            }
        });




//        adView = new AdView(getContext(), utils.getAdId(), AdSize.BANNER_HEIGHT_50);
//
//// Find the Ad Container
//        LinearLayout adContainer = (LinearLayout) v.findViewById(R.id.banner_container);
//
//// Add the ad view to your activity layout
//        adContainer.addView(adView);
//
//// Request an ad
//        adView.loadAd();



        return v;
    }
    private void difficulty(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View x=getLayoutInflater().inflate(R.layout.dificulty,null);
        builder.setView(x);
        AlertDialog dialog=builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        radioGroup=(RadioGroup)x.findViewById(R.id.btngrp);
        sharedPreferences=getActivity().getSharedPreferences("Difficulty", Context.MODE_PRIVATE);
        int status=sharedPreferences.getInt("value",20);
        switch (status){
            case 20:
                radioGroup.check(R.id.Easy);
                break;
            case 40:
                radioGroup.check(R.id.Medium);
                break;
            case 60:
                radioGroup.check(R.id.Hard);
                break;

        }

        x.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int btnid =radioGroup.getCheckedRadioButtonId();
               radioButton=x.findViewById(btnid);
                SharedPreferences.Editor editor=getActivity().getSharedPreferences("Difficulty",Context.MODE_PRIVATE).edit();

                if(btnid==R.id.Easy){

                    editor.putInt("value",20);
                }
                if(btnid==R.id.Medium){

                    editor.putInt("value",40);
                }
                if(btnid==R.id.Hard){
                    editor.putInt("value",60);
                }
                editor.apply();
                Toast.makeText(getContext(),"Difficulty was set to "+radioButton.getText()+" mode.",Toast.LENGTH_LONG).show();

                dialog.dismiss();

            }
        });
        x.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();


    }


}