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
import com.example.moviedb.model.Upcoming;

import java.util.List;

public class UpComing_adapter extends RecyclerView.Adapter<UpComing_adapter.UpComingHolder> {

    private Context context;
    private List<Upcoming.Results> listUpComing;
    private List<Upcoming.Results> getListUpComing(){return listUpComing;}
    public void setListUpComing(List<Upcoming.Results>listUpComing){
        this.listUpComing = listUpComing;
    }

    public UpComing_adapter (Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public UpComingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_up_coming, parent, false);
        return new UpComing_adapter.UpComingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpComing_adapter.UpComingHolder holder, int position) {
        final Upcoming.Results results = getListUpComing().get(position);
        holder.lbl_title.setText(results.getTitle());
        holder.lbl_overview.setText(results.getOverview());
        holder.lbl_release_date.setText(results.getRelease_date());
        Glide.with(context).load(Const.IMG_URL+results.getPoster_path())
                .into(holder.img_poster);
    }

    @Override
    public int getItemCount() {
        return getListUpComing().size();
    }

    public class UpComingHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView lbl_title, lbl_overview, lbl_release_date;
        CardView cv;

        public UpComingHolder(@NonNull View itemView) {
            super(itemView);
            lbl_title = itemView.findViewById(R.id.lbl_title_card);
            lbl_overview = itemView.findViewById(R.id.lbl_overview_card);
            lbl_release_date = itemView.findViewById(R.id.lbl_releasedate_card);
            img_poster = itemView.findViewById(R.id.img_poster_upcoming);
            cv = itemView.findViewById(R.id.cv_card_upcoming);
        }
    }
}

