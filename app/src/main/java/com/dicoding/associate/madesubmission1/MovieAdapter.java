package com.dicoding.associate.madesubmission1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.associate.model.Movie;

import java.util.List;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private int rowLayout;
    private Context context;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        TextView movieDesc;
        TextView movieRelease;
        ImageView movieImage;


        public MovieViewHolder(View v) {
            super(v);
            movieTitle = v.findViewById(R.id.tv_title);
            movieDesc = v.findViewById(R.id.tv_detail);
            movieRelease = v.findViewById(R.id.tv_date);
            movieImage = v.findViewById(R.id.img_movie);
        }
    }

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, null, false);
//        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(layoutParams);
        return new MovieViewHolder(view);
    }


    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.movieDesc.setText(movies.get(position).getSynopsis());
        holder.movieRelease.setText(movies.get(position).getReleaseDate());

        String urlimage = "";
        if (movies.get(position).getPosterPath() != null) {
            urlimage = MainActivity.baseUrlImage+movies.get(position).getPosterPath().toString();
            Uri myUri = Uri.parse(urlimage);
            Glide.with(this.context)
                    .load(myUri)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.capamerica))
                    .into(holder.movieImage);

        } else {
            holder.movieImage.setImageDrawable(null);
        }

        final String finalUrlimage = urlimage;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(context,DetailMovie.class);
                detailIntent.putExtra("title",movies.get(position).getTitle());
                detailIntent.putExtra("desc",movies.get(position).getSynopsis());
                detailIntent.putExtra("releasedate",movies.get(position).getReleaseDate());
                detailIntent.putExtra("urlimage", finalUrlimage);
                v.getContext().startActivity(detailIntent);

            }
        });
    }

    public int getItemCount() {
        return movies.size();
    }
}
