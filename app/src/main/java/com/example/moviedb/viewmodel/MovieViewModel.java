package com.example.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.model.Popular;
import com.example.moviedb.model.Upcoming;
import com.example.moviedb.repositories.MovieRepository;

import java.util.Objects;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;


    public MovieViewModel(@NonNull Application application){
        super(Objects.requireNonNull(application));
        repository = MovieRepository.getInstance();
    }

    //==Start of viewmodel get movie by ID
    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void getMovieById(String movieId){
        resultGetMovieById = repository.getMovieData(movieId);
    }
    public LiveData<Movies> getResultGetMovieById(){
        return resultGetMovieById;
    }


    //==End of viewmodel Now Playing

    //==Start of viewmodel get movie by ID
    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repository.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultGetNowPlaying(){
        return resultGetNowPlaying;
    }


    //==End of viewmodel get Now Playing

    //==Start of viewmodel get Upcoming
    private MutableLiveData<Upcoming> resultGetUpComing = new MutableLiveData<>();
    public void getUpComing(){
        resultGetUpComing = repository.getUpcomingData();
    }
    public LiveData<Upcoming> getResultGetUpComing(){
        return resultGetUpComing;
    }


    //==End of viewmodel get Upcoming

    //==Start of viewmodel get Popular
    private MutableLiveData<Popular> resultGetPopular = new MutableLiveData<>();
    public void getPopular(){
        resultGetPopular = repository.getPopularData();
    }
    public LiveData<Popular> getResultGetPopular(){
        return resultGetPopular;
    }

    //==End of viewmodel get Popular
}
