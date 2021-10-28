package com.example.moviedb.view.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.adapter.Company_adapter;
import com.example.moviedb.helper.Const;
import com.example.moviedb.helper.ItemClickSupport;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = ProgressDialog.show(getActivity(),"", "Loading.. ", true);
        dialog.show();

    }

    private MovieViewModel view_model;
    private TextView lbl_movie_description,lbl_movie_genre,
            lbl_movie_rate, lbl_movie_title,lbl_movie_date;
    private ImageView lbl_movie_poster, lbl_movie_backdrop;
    private String genres = "";
    private RecyclerView rv_logo;
    private ProgressDialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        lbl_movie_description = view.findViewById(R.id.fragment_movie_detail_description);
        lbl_movie_title = view.findViewById(R.id.fragment_movie_detail_title);
        lbl_movie_genre = view.findViewById(R.id.fragment_movie_detail_genre);
        lbl_movie_rate = view.findViewById(R.id.fragment_movie_detail_rating);
        lbl_movie_poster = view.findViewById(R.id.fragment_movie_detail_image);
        lbl_movie_backdrop = view.findViewById(R.id.fragment_movie_detail_backdrop);
        lbl_movie_date = view.findViewById(R.id.fragment_movie_detail_date);
        rv_logo = view.findViewById(R.id.rv_logo);
        String movieID = getArguments().getString("movieId");
        view_model = new ViewModelProvider(MovieDetailsFragment.this).get(MovieViewModel.class);
        view_model.getMovieById(movieID);
        view_model.getResultGetMovieById().observe(getActivity(), moviedetail);


        return view;
    }

    private Observer<Movies> moviedetail = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {

            String title = movies.getTitle();
            String overview= movies.getOverview();
            String date = movies.getRelease_date();
            String vote = String.valueOf(movies.getVote_average());

            lbl_movie_title.setText(title);
            lbl_movie_description.setText(overview);
            lbl_movie_date.setText(date);
            lbl_movie_rate.setText(vote);
            String img_path = Const.IMG_URL + movies.getPoster_path().toString();
            String img_back = Const.IMG_URL + movies.getBackdrop_path();
            Glide.with(MovieDetailsFragment.this).load(img_path).into(lbl_movie_poster);
            Glide.with(MovieDetailsFragment.this).load(img_back).into(lbl_movie_backdrop);
            for (int i = 0; i <movies.getGenres().size(); i++){
                if (i ==movies.getGenres().size()-1){
                    genres += movies.getGenres().get(i).getName();
                }else{
                    genres += movies.getGenres().get(i).getName()+ " , ";
                }
            }
            lbl_movie_genre.setText(genres);
            rv_logo.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.HORIZONTAL, false));
            Company_adapter adapter = new Company_adapter(getActivity());
            adapter.setCompany(movies.getProduction_companies());
            rv_logo.setAdapter(adapter);
            dialog.dismiss();
        }
    };
}