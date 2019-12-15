package com.yourid.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {

    @BindView(R.id.indeterminateBar)
    ProgressBar mProgressBar;

    @BindView(R.id.pop_movies_grid0)
    GridView mGridView0;

    @BindView(R.id.search_label)
    TextView mSearchText;

    @BindView(R.id.editTextSearch)
    TextView mEditText;

    @BindView(R.id.buttonSearch)
    Button mButtonSearch;

    @BindView(R.id.pop_movies_grid)
    GridView mGridView;

    @BindView(R.id.pop_movies_grid2)
    GridView mGridView2;

    ArrayList<Movie> mPopularList;
    ArrayList<Movie> mTopTopRatedList;
    ArrayList<Movie> mSearchList = new ArrayList<>();

    String popularMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="+"4093f7f8446b10a772f58889028d0c02";
    String topRatedMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key="+"4093f7f8446b10a772f58889028d0c02";

    String searchUrl = "https://api.themoviedb.org/3/search/movie?api_key=4093f7f8446b10a772f58889028d0c02&language=en-US&page=1&include_adult=false&query=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this);
        mProgressBar.setVisibility(View.INVISIBLE);

        new FetchMovies().execute();

        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mEditText.getText().toString().trim().equalsIgnoreCase("")) {

                    if(NetUtils.networkStatus(MovieActivity.this)){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    mSearchList = NetUtils.fetchData(searchUrl+mEditText.getText().toString().trim());
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            refreshList(mPopularList, mTopTopRatedList, mSearchList);
                                            mSearchText.setVisibility(View.VISIBLE);
                                            mGridView0.setVisibility(View.VISIBLE);
                                        }
                                    });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.e("findme", "DONE");
                            }
                        }).start();
                    }else{
                        Toast.makeText(MovieActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        mGridView0.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int position, long l) {
                Movie movie = (Movie) view.getAdapter().getItem(position);
                Intent intent = new Intent(MovieActivity.this, MovieDetailActivity.class);
                intent.putExtra("detail", movie);
                startActivity(intent);
                Log.e("findme", "Clicked"+movie.toString());
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int position, long l) {
                Movie movie = (Movie) view.getAdapter().getItem(position);
                Intent intent = new Intent(MovieActivity.this, MovieDetailActivity.class);
                intent.putExtra("detail", movie);
                startActivity(intent);
                Log.e("findme", "Clicked"+movie.toString());
            }
        });

        mGridView2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int position, long l) {
                Movie movie = (Movie) view.getAdapter().getItem(position);
                Intent intent = new Intent(MovieActivity.this, MovieDetailActivity.class);
                intent.putExtra("detail", movie);
                startActivity(intent);
                Log.e("findme", "Clicked"+movie.toString());
            }
        });
    }

    private void refreshList(ArrayList<Movie> list, ArrayList<Movie> popList, ArrayList<Movie> searchList) {
        Adapter adapter0 = new Adapter(MovieActivity.this, searchList);
        Adapter adapter = new Adapter(MovieActivity.this, list);
        Adapter adapter2 = new Adapter(MovieActivity.this, popList);

        mGridView0.invalidateViews();
        mGridView0.setAdapter(adapter0);

        mGridView.invalidateViews();
        mGridView.setAdapter(adapter);

        mGridView2.invalidateViews();
        mGridView2.setAdapter(adapter2);
    }



    //AsyncTask
    public class FetchMovies extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            mPopularList = new ArrayList<>();
            mTopTopRatedList = new ArrayList<>();

            try {
                if(NetUtils.networkStatus(MovieActivity.this)){
                    mPopularList = NetUtils.fetchData(popularMoviesURL);
                    mTopTopRatedList = NetUtils.fetchData(topRatedMoviesURL);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshList(mPopularList, mTopTopRatedList, mSearchList);
                        }
                    });
                    Log.e("findme", "DONE");
                }else{
                    Toast.makeText(MovieActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }



}
