package com.zerotechy.pregnancyfitness;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Formatter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BMIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BMIFragment extends Fragment {

    TextView height,weight,result,yourbmi;
    ImageView button;
    SeekBar sheight,sweight;
    double heightvalue,weightvalue;
    boolean repeat=false;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BMIFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BMIFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BMIFragment newInstance(String param1, String param2) {
        BMIFragment fragment = new BMIFragment();
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
        View v=inflater.inflate(R.layout.fragment_b_m_i,container,false);

        height=(TextView)v.findViewById(R.id.heightamount);
        weight=(TextView)v.findViewById(R.id.weightamount);
        yourbmi=(TextView)v.findViewById(R.id.yourbmi);yourbmi.setVisibility(View.INVISIBLE);
        result=(TextView)v.findViewById(R.id.result);
         result.setVisibility(View.INVISIBLE);
        sheight=(SeekBar)v.findViewById(R.id.heightbar);
        sweight=(SeekBar)v.findViewById(R.id.weighttbar);
        button=(ImageView)v.findViewById(R.id.bmigobtn);

        sheight.setMax(250);
        sheight.setProgress(0,true);
        sweight.setProgress(0);
        heightvalue=(double) sheight.getProgress();
        weightvalue=(double)sweight.getProgress();
        sheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                height.setText(String.valueOf(progress)+" cm");
                heightvalue=(double) progress/100;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

            sweight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    weight.setText(String.valueOf(progress)+" kg");
                    weightvalue=(double)progress;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!repeat){
                        try {
                        heightvalue=heightvalue*heightvalue;
                        double newresult=weightvalue/heightvalue;
                        Formatter formatter = new Formatter();
                        formatter.format("%.2f", newresult);
                        result.setText(String.valueOf(formatter));
                        result.setVisibility(View.VISIBLE);
                        yourbmi.setVisibility(View.VISIBLE);
//                        Toast.makeText(getContext(), String.valueOf(heightvalue)+"  "+String.valueOf(weightvalue), Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();

                    }

                        sheight.setEnabled(false);
                        sweight.setEnabled(false);
                        button.setImageResource(R.drawable.repeatbtnbmi);
                        repeat=true;

                    }
                    else {
                        sheight.setEnabled(true);
                        sweight.setEnabled(true);
                        sheight.setProgress(0,true);
                        sweight.setProgress(0,true);
                        button.setImageResource(R.drawable.bmi_go);
                        result.setVisibility(View.INVISIBLE);
                        yourbmi.setVisibility(View.INVISIBLE);
                        repeat=false;
                    }




                    }





            });




        return v;
    }
}