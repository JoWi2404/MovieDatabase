package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private MovieViewModel view_model;
    private TextView lbl_text, lbl_title,lbl_overview,lbl_date, lbl_genre, lbl_status;
    private ImageView img_poster;
    private String movie_id = "", genres = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");


        view_model = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        view_model.getMovieById(movie_id);
        view_model.getResultGetMovieById().observe(MovieDetailsActivity.this, showMovieDetails);

        img_poster = findViewById(R.id.img_poster_movie_details);
        lbl_genre = findViewById(R.id.lbl_genre_movie_details);
        lbl_title = findViewById(R.id.lbl_title_movie_details);
        lbl_overview = findViewById(R.id.lbl_overview_movie_details);
        lbl_date = findViewById(R.id.lbl_release_date_movie_details);
        lbl_status = findViewById(R.id.lbl_status_move_details);
        lbl_text = findViewById(R.id.lbl_movie_details);
        lbl_text.setText(movie_id);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private Observer<Movies> showMovieDetails = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String title = movies.getTitle(), overview = movies.getOverview(), date = movies.getRelease_date(), status = movies.getStatus();

            lbl_title.setText(title);
            lbl_overview.setText(overview);
            lbl_date.setText(date);
            String img_path = Const.IMG_URL + movies.getPoster_path().toString();
            Glide.with(MovieDetailsActivity.this).load(img_path).into(img_poster);
            for (int i = 0; i <movies.getGenres().size(); i++){
                if (i ==movies.getGenres().size()-1){
                    genres += movies.getGenres().get(i).getName();
                }else{
                    genres += movies.getGenres().get(i).getName()+ " , ";
                }
            }
            lbl_genre.setText(genres);
            lbl_status.setText(status);
        }
    };
}