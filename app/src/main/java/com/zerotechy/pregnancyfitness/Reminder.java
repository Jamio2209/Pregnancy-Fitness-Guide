package com.zerotechy.pregnancyfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.Calendar;

public class Reminder extends AppCompatActivity {

    Button reminder;
    Switch aSwitch;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        reminder=(Button)findViewById(R.id.settime);
        aSwitch=(Switch)findViewById(R.id.notificationswitch);

        sharedPreferences =getSharedPreferences("Reminder",MODE_PRIVATE);
        aSwitch.setChecked(sharedPreferences.getBoolean("value",false));

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aSwitch.isChecked()){
                  SharedPreferences.Editor editor=getSharedPreferences("Reminder",MODE_PRIVATE).edit();
                  editor.putBoolean("value",true);
                  editor.apply();
                  aSwitch.setChecked(true);
                }
                else{
                    SharedPreferences.Editor editor=getSharedPreferences("Reminder",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    aSwitch.setChecked(false);
                    stopAlarm();
                }
            }
        });

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int hour=c.get(Calendar.HOUR_OF_DAY);
                int min=c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog=new TimePickerDialog(Reminder.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        c.set(Calendar.SECOND,0);
                        startAlarm(c);


                    }
                },hour,min, DateFormat.is24HourFormat(Reminder.this));
                timePickerDialog.updateTime(hour,min);
                timePickerDialog.show();

            }
        });
    }

    public void startAlarm(Calendar c){

        AlarmManager alarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i=new Intent(this,AlertReciever.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,1,i,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);

        SharedPreferences.Editor editor=getSharedPreferences("Reminder",MODE_PRIVATE).edit();
        editor.putBoolean("value",true);
        editor.apply();
        aSwitch.setChecked(true);



    }
    public void stopAlarm(){
        AlarmManager alarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i=new Intent(this,AlertReciever.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,1,i,0);
        alarmManager.cancel(pendingIntent);
    }



}