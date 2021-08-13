package com.zerotechy.pregnancyfitness;


import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.AnimationUtils;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    List<Tips> allexercise;
    Context context;
    public ExerciseAdapter(Context context,List<Tips> allexercise) {
        this.allexercise = allexercise;
        this.context=context;
    }


    @NonNull
    @NotNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.exerciseview,parent,false);

        return new ExerciseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ExerciseViewHolder holder, int position) {

//        holder.relativeLayout.animate().translationX(-2000).start();

        holder.title.setText(allexercise.get(position).getTitle());
        Glide.with(holder.itemView).load(allexercise.get(position).getThumb()).into(holder.thumb);
        holder.relativeLayout.animate().translationX(-1).setDuration(1000).start();
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent i=new Intent(v.getContext(),ExerciseDetails.class);
                i.putExtra("title",allexercise.get(position).getTitle());
                i.putExtra("img",allexercise.get(position).getThumb());
                i.putExtra("desc",allexercise.get(position).getContent().replaceAll("\\<.*?\\>", ""));
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allexercise.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder{

        ImageView thumb;
        TextView title;
        ImageView click;
        RelativeLayout relativeLayout;
        public ExerciseViewHolder(View itemview){
            super(itemview);
            thumb=(ImageView)itemview.findViewById(R.id.pose);
            title=(TextView)itemview.findViewById(R.id.exercise_title);
            click=(ImageView)itemview.findViewById(R.id.btnclick);
            relativeLayout=(RelativeLayout)itemview.findViewById(R.id.mainlayout);

        }

    }
}
