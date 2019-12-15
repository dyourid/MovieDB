package com.yourid.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();

    public static final String BASE_URL ="https://image.tmdb.org/t/p/w185";

    @BindView(R.id.movieTitle)
    TextView mTitle;

    @BindView(R.id.movieDesc)
    TextView mDesc;

    @BindView(R.id.avg)
    TextView mAvg;

    @BindView(R.id.moviePoster)
    ImageView mPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        Movie mov_intent = (Movie)        intent.getSerializableExtra("detail");

        ButterKnife.bind(this);

        mTitle.setText(mov_intent.getOriginalTitle());
        mDesc.setText(mov_intent.getOverview());
        mAvg.setText("User Rating: " + mov_intent.getVoteAverage());

        Picasso.get()
                .load(BASE_URL+mov_intent.getPosterPath())
                .into(mPoster);

    }
}
