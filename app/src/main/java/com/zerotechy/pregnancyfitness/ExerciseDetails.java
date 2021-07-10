package com.zerotechy.pregnancyfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.microedition.khronos.opengles.GL;

public class ExerciseDetails extends AppCompatActivity {



    TextView titletext,contenttext;ImageView thumb;
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

    }
}