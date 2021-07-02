package com.zerotechy.pregnancyfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Dashboard extends AppCompatActivity {

    private MeowBottomNavigation bnv_Main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bnv_Main=findViewById(R.id.bottomnav);
        bnv_Main.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bnv_Main.add(new MeowBottomNavigation.Model(2,R.drawable.ic_list));
        bnv_Main.add(new MeowBottomNavigation.Model(3,R.drawable.ic_bmi));
        bnv_Main.add(new MeowBottomNavigation.Model(4,R.drawable.ic_settings));



        bnv_Main.show(1,true);
        replace(new HomeFragment());
        bnv_Main.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new HomeFragment());
                        bnv_Main.clearCount(2);

                        break;

                    case 2:
                        replace(new ExerciseFragment());
                        break;

                    case 3:
                        replace(new BMIFragment());
                        break;

                    case 4:
                        replace(new SettingsFragment());
                        break;

                }
                return null;
            }
        });




    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainframe,fragment);
        transaction.commit();
    }
}