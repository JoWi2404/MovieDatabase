package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Popular;

import java.util.List;

import javax.xml.transform.Result;

public class Popular_adapter extends RecyclerView.Adapter<Popular_adapter.PopularHolder> {

    private Context context;
    private List<Popular.Results> lisitPopular;
    private List<Popular.Results> getLisitPopular(){return lisitPopular;}
    public void setListPopular(List<Popular.Results>lisitPopular){
        this.lisitPopular = lisitPopular;
    }

    public Popular_adapter (Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public PopularHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_popular,parent,false);
        return new Popular_adapter.PopularHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularHolder holder, int position) {
        final Popular.Results results = getLisitPopular().get(position);
        holder.lbl_title.setText(results.getTitle());
        holder.lbl_overview.setText(results.getOverview());
        holder.lbl_release_date.setText(results.getRelease_date());
        Glide.with(context).load(Const.IMG_URL+results.getPoster_path())
                .into(holder.img_poster);
    }

    @Override
    public int getItemCount() {
         return getLisitPopular().size();
    }

    public class PopularHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView lbl_title, lbl_overview, lbl_release_date;
        CardView cv;


        public PopularHolder(@NonNull View itemView) {
            super(itemView);
            lbl_title = itemView.findViewById(R.id.lbl_title_popular);
            lbl_overview = itemView.findViewById(R.id.lbl_overview_popular);
            lbl_release_date = itemView.findViewById(R.id.lbl_releasedate_popular);
            img_poster = itemView.findViewById(R.id.img_poster_popular);
            cv = itemView.findViewById(R.id.cv_card_popular);

        }
    }
}
