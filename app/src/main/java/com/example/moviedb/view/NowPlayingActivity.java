package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.moviedb.R;
import com.example.moviedb.adapter.NowPlaying_adapter;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.viewmodel.MovieViewModel;

public class NowPlayingActivity extends AppCompatActivity {

    private RecyclerView rv_now_playing;
    private MovieViewModel view_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        getSupportActionBar().hide();
        view_model = new ViewModelProvider(NowPlayingActivity.this).get(MovieViewModel.class);
        rv_now_playing = findViewById(R.id.rv_now_playing);
        view_model.getNowPlaying();
        view_model.getResultGetNowPlaying().observe(NowPlayingActivity.this, showNowPlaying);

    }
    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            rv_now_playing.setLayoutManager(new LinearLayoutManager(NowPlayingActivity.this));
            NowPlaying_adapter adapter = new NowPlaying_adapter(NowPlayingActivity.this);
            adapter.setListNowPlaying(nowPlaying.getResults());
            rv_now_playing.setAdapter(adapter);
        }
    };

}