package com.zerotechy.pregnancyfitness;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {

    List<Tips> allTips;
    public TipsAdapter(List<Tips> allTips) {
        this.allTips = allTips;
    }




    @NonNull
    @NotNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    LayoutInflater inflater=LayoutInflater.from(parent.getContext());
    View v=inflater.inflate(R.layout.tipsview,parent,false);

        return new TipsViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull TipsViewHolder holder, int position) {

        holder.title.setText(allTips.get(position).getTitle());
       String text=allTips.get(position).getContent();

        holder.content.setText(allTips.get(position).getContent().replaceAll("\\<.*?\\>", ""));
        Glide.with(holder.itemView).load(allTips.get(position).getThumb()).into(holder.thumb);
    }

    @Override
    public int getItemCount() {
        return allTips.size();
    }

    public class TipsViewHolder extends RecyclerView.ViewHolder{

        ImageView thumb;
        TextView title,content;
        CardView cardView;
        public TipsViewHolder(View itemView){
            super(itemView);
            thumb=(ImageView) itemView.findViewById(R.id.thumbnail);
            title=(TextView) itemView.findViewById(R.id.title);
            content=(TextView)itemView.findViewById(R.id.content);
//            cardView=(CardView)itemView.findViewById(R.id.thumbnailcard);
//            cardView.setBackgroundResource(R.drawable.tips_img_bg);

        }
    }
}
