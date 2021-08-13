package com.zerotechy.pregnancyfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.inputmethod.InputConnectionCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL;

public class ExerciseDetails extends AppCompatActivity {


    private MediaPlayer mediaPlayer,endplayer;
    TextView titletext,contenttext,timertxt;
    ImageView thumb,backbtn;
    Button startbtn;
    Boolean started=true;
    private int second=20;
    CountDownTimer timer;
    SharedPreferences sharedPreferences;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        Intent intent = getIntent();

        titletext=(TextView)findViewById(R.id.exercise_title);
        try {
            titletext.setText(intent.getStringExtra("title"));
        }catch (Exception e){

        }

        thumb=(ImageView)findViewById(R.id.pose);
        Glide.with(this).load(intent.getStringExtra("img")).into(thumb);
        contenttext=(TextView)findViewById(R.id.contenttext);
        contenttext.setText(intent.getStringExtra("desc"));
        backbtn=(ImageView)findViewById(R.id.backbtn);
        timertxt=(TextView)findViewById(R.id.timertxt);
        startbtn=(Button)findViewById(R.id.startbtn);
        sharedPreferences=getSharedPreferences("Difficulty",MODE_PRIVATE);
        timertxt.setText(String.valueOf(sharedPreferences.getInt("value",20)));


        adView = new AdView(this, utils.getAdId(), AdSize.BANNER_HEIGHT_50);

// Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

// Add the ad view to your activity layout
        adContainer.addView(adView);

// Request an ad
        adView.loadAd();
        startbtn.animate().translationX(-1).setDuration(1700).start();
        timertxt.animate().alpha(1f).setDuration(2000).start();



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
                    endplayer=new MediaPlayer();
                    AssetFileDescriptor fileDescriptor=getResources().openRawResourceFd(R.raw.countsound);
                    try {
                        mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());
                        fileDescriptor.close();
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                timer=new CountDownTimer(sharedPreferences.getInt("value",20)*1000+500,1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        timertxt.setText(String.valueOf((int) (millisUntilFinished/1000)));
                                    }

                                    @Override
                                    public void onFinish() {
                                        startbtn.setText("Start");
                                        started=true;
                                        timertxt.setText("20");
                                        try {
                                            AssetFileDescriptor nfileDescriptor=getResources().openRawResourceFd(R.raw.endsound);
                                            endplayer.setDataSource(nfileDescriptor.getFileDescriptor(),nfileDescriptor.getStartOffset(),nfileDescriptor.getLength());
                                            endplayer.prepare();
                                            endplayer.start();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

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
                    timertxt.setText(String.valueOf(sharedPreferences.getInt("value",20)));
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