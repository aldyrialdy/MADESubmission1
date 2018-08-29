package com.dicoding.associate.madesubmission1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;

public class DetailMovie extends AppCompatActivity {

    @BindView(R.id.det_tv_title)
    TextView movieTitle;
    @BindView(R.id.det_tv_detail)
    TextView movieDesc;
    @BindView(R.id.det_tv_date)
    TextView movieRelease;
    @BindView(R.id.det_img_movie)
    ImageView movieImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        movieTitle = findViewById(R.id.det_tv_title);
        movieDesc = findViewById(R.id.det_tv_detail);
        movieRelease = findViewById(R.id.det_tv_date);
        movieImage = findViewById(R.id.det_img_movie);

        String titleMovie = getIntent().getStringExtra("title");
        String descMovie = getIntent().getStringExtra("desc");
        String releaseMovie = getIntent().getStringExtra("releasedate");
        String imageUrl = getIntent().getStringExtra("urlimage");

        movieTitle.setText(titleMovie);
        movieDesc.setText(descMovie);
        movieRelease.setText(releaseMovie);

        if (imageUrl != null) {
            Uri myUri = Uri.parse(imageUrl);
            Glide.with(getApplicationContext())
                    .load(myUri)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.capamerica))
                    .into(movieImage);

        } else {
            movieImage.setImageDrawable(null);
        }

    }
}
