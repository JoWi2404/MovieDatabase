package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;

import java.util.List;

public class Company_adapter extends RecyclerView.Adapter<Company_adapter.CompanyHolder>{

    private Context context;
    private List<Movies.ProductionCompanies> company;

    public Company_adapter(Context context) {
        this.context = context;
    }

    public List<Movies.ProductionCompanies> getCompany() {
        return company;
    }

    public void setCompany(List<Movies.ProductionCompanies> company) {
        this.company = company;
    }

    public Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public Company_adapter.CompanyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_company, parent, false);
        return new CompanyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Company_adapter.CompanyHolder holder, int position) {
        final Movies.ProductionCompanies companies = getCompany().get(position);
        Glide.with(context).load(Const.IMG_URL+companies.getLogo_path()).into(holder.logo);
        holder.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, companies.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getCompany().size();
    }

    public class CompanyHolder extends RecyclerView.ViewHolder {

        ImageView logo;
        public CompanyHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.company_logo);
        }
    }
}
