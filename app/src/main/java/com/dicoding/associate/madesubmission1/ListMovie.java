package com.dicoding.associate.madesubmission1;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dicoding.associate.model.Movie;
import com.dicoding.associate.model.MovieResponse;
import com.dicoding.associate.rest.ApiClient;
import com.dicoding.associate.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMovie extends Fragment {
    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;
    List<Movie> listMoview = new ArrayList<>();
    ProgressDialog loading;
    ApiInterface apiService;
    View rootView;

    private MovieAdapter adapter;


    public ListMovie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_movie, container, false);
        ButterKnife.bind(this, rootView);
        String strtext = getArguments().getString("queryget");
        adapter = new MovieAdapter(listMoview, getActivity());
//        rvMovies.setHasFixedSize(true);
//        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
//        rvMovies.setAdapter(adapter);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiService.getMovieSearch(MainActivity.API_KEY, "en-US", strtext);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    listMoview = response.body().getResults();

                    rvMovies.setHasFixedSize(true);
                    rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
                    rvMovies.setAdapter(new MovieAdapter(listMoview, getContext()));
                    adapter.notifyDataSetChanged();
                    Log.d(MainActivity.TAG, "Number of movies received: " + listMoview.size());
                } else {
                    loading.dismiss();
                    Toast.makeText(getContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Log error here since request failed
//                Log.e(MainActivity.TAG, t.toString());
//                loading.dismiss();
                Toast.makeText(getContext(), "Failed to Connect Internet !", Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

}
