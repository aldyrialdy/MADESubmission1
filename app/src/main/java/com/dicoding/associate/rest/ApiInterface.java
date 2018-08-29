package com.dicoding.associate.rest;

import com.dicoding.associate.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search/movie")
    Call<MovieResponse> getMovieSearch(@Query("api_key") String apiKey, @Query("language") String lang,@Query("query") String query);
}
