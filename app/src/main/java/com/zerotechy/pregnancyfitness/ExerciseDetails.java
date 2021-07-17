package com.zerotechy.pregnancyfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.inputmethod.InputConnectionCompat;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL;

public class ExerciseDetails extends AppCompatActivity {


    private MediaPlayer mediaPlayer;
    TextView titletext,contenttext,timertxt;
    ImageView thumb,backbtn;
    Button startbtn;
    Boolean started=true;
    private int second=20;
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        Intent intent = getIntent();

        titletext=(TextView)findViewById(R.id.titletext);
        titletext.setText(intent.getStringExtra("title"));
        thumb=(ImageView)findViewById(R.id.pose);
        Glide.with(this).load(intent.getStringExtra("img")).into(thumb);
        contenttext=(TextView)findViewById(R.id.contenttext);
        contenttext.setText(intent.getStringExtra("desc"));
        backbtn=(ImageView)findViewById(R.id.backbtn);
        timertxt=(TextView)findViewById(R.id.timertxt);
        startbtn=(Button)findViewById(R.id.startbtn);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (started){
                    started=false;
                    startbtn.setText("Stop");
                    mediaPlayer=new MediaPlayer();
                    AssetFileDescriptor fileDescriptor=getResources().openRawResourceFd(R.raw.countsound);
                    try {
                        mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());
                        fileDescriptor.close();
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                timer=new CountDownTimer(20500,1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        timertxt.setText(String.valueOf((int) (millisUntilFinished/1000)));
                                    }

                                    @Override
                                    public void onFinish() {
                                        startbtn.setText("Start");
                                        started=true;
                                        timertxt.setText("20");

                                    }
                                }.start();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }





                }else {
                    timer.cancel();
                    mediaPlayer.stop();
                    startbtn.setText("Start");
                    timertxt.setText("20");
                    started=true;
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}