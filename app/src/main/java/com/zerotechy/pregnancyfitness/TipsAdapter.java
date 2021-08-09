package com.zerotechy.pregnancyfitness;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.facebook.ads.AdSize;
//import com.facebook.ads.AdView;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.material.imageview.ShapeableImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter {

    List<Tips> allTips;

    public TipsAdapter(List<Tips> allTips) {
        this.allTips = allTips;
    }




    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v;


                v=inflater.inflate(R.layout.tipsview,parent,false);
                return new TipsViewHolder(v);

    
    


    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {



                TipsViewHolder tipsViewHolder=(TipsViewHolder) holder;
                tipsViewHolder.title.setText(allTips.get(position).getTitle());
                String text=allTips.get(position).getContent();
                tipsViewHolder.content.setText(allTips.get(position).getContent().replaceAll("\\<.*?\\>", ""));
                Glide.with(tipsViewHolder.itemView).load(allTips.get(position).getThumb()).into(tipsViewHolder.thumb);






//        holder.title.setText(allTips.get(position).getTitle());
//       String text=allTips.get(position).getContent();
//
//        holder.content.setText(allTips.get(position).getContent().replaceAll("\\<.*?\\>", ""));
//        Glide.with(holder.itemView).load(allTips.get(position).getThumb()).into(holder.thumb);

    }

    @Override
    public int getItemCount() {
        return allTips.size();
    }

    public class TipsViewHolder extends RecyclerView.ViewHolder{

        ShapeableImageView thumb;
        TextView title,content;

        public TipsViewHolder(View itemView){
            super(itemView);
            thumb=(ShapeableImageView) itemView.findViewById(R.id.thumbnail);
            title=(TextView) itemView.findViewById(R.id.title);
            content=(TextView)itemView.findViewById(R.id.content);


        }
    }



}
