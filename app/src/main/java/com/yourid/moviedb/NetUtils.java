package com.yourid.moviedb;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.google.common.io.ByteStreams;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetUtils {

    private static final String TAG = NetUtils.class.getSimpleName();


    public static ArrayList<Movie> fetchData(String url) throws IOException {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {

            URL new_url = new URL(url); //create a url from a String
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection(); //Opening a http connection  to the remote object
            connection.connect();

            InputStream inputStream = connection.getInputStream(); //reading from the object
            String results = new String(ByteStreams.toByteArray(inputStream ));
            parseJson(results,movies);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public static void parseJson(String data, ArrayList<Movie> list){

        try {
            JSONObject mainObject = new JSONObject(data);

            JSONArray resArray = mainObject.getJSONArray("results");
            for (int i = 0; i < resArray.length(); i++) {
                JSONObject jsonObject = resArray.getJSONObject(i);
                Movie movie = new Movie();
                movie.setId(jsonObject.getInt("id"));
                movie.setVoteAverage(jsonObject.getInt("vote_average"));
                movie.setVoteCount(jsonObject.getInt("vote_count"));
                movie.setOriginalTitle(jsonObject.getString("original_title"));
                movie.setTitle(jsonObject.getString("title"));
                movie.setPopularity(jsonObject.getDouble("popularity"));
                movie.setBackdropPath(jsonObject.getString("backdrop_path"));
                movie.setOverview(jsonObject.getString("overview"));
                movie.setReleaseDate(jsonObject.getString("release_date"));
                movie.setPosterPath(jsonObject.getString("poster_path"));


                list.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Erro occurred during JSON Parsing", e);
        }

    }

    public static Boolean networkStatus(Context context){
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

}
