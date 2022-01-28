package com.example.moviesappandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Struct;
import java.util.ArrayList;


public class BackupMainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<PopularMovie> popularMovies,playingMovies;
    private static String JSON_URL_POPULAR="https://api.themoviedb.org/3/movie/popular?api_key=9a1459605005a74e4827ce412d685208";
    private static String JSON_URL_NOW_PLAYING="https://api.themoviedb.org/3/movie/now_playing?api_key=9a1459605005a74e4827ce412d685208";
    private  static  RecyclerView recyclerView,recyclerView2;
    private  static MovieListAdapter movieListAdapter;
    private static MovieListAdapter2 movieListAdapter2;
    private static RecyclerView.LayoutManager layoutManager,layoutManager2;


    private ImageButton generbuttonhome,tvshowbuttonhome,goprobuttonhome;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backup_activity_main);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.darkblue));

        generbuttonhome=findViewById(R.id.genrebuttonhome);
        tvshowbuttonhome=findViewById(R.id.tvshowbuttonhome);
        goprobuttonhome=findViewById(R.id.goprobuttonhome);

        generbuttonhome.setOnClickListener(this);
        tvshowbuttonhome.setOnClickListener(this);
        goprobuttonhome.setOnClickListener(this);



        recyclerView=findViewById(R.id.recyclerviewone);
        recyclerView2=findViewById(R.id.recyclerviewhome2);



        popularMovies=new ArrayList<>();
        playingMovies=new ArrayList<>();

        GetData getData=new GetData();
        getData.execute(JSON_URL_POPULAR);

        new GetData2().execute(JSON_URL_NOW_PLAYING);
//






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.genrebuttonhome:
                Intent igenry=new Intent(this,GenereActivityHome.class);
                startActivity(igenry);
                break;
            case R.id.tvshowbuttonhome:
                Intent itvshow=new Intent(this,TvshowActivityHome.class);
                startActivity(itvshow);
                break;
            case R.id.goprobuttonhome:
                Intent igopro=new Intent(this,GoProActivityHome.class);
                startActivity(igopro);
                break;

        }

    }

    public class GetData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings){
            String current="";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url=new URL(strings[0]);
                    urlConnection=(HttpURLConnection) url.openConnection();
                    InputStream is=urlConnection.getInputStream();
                    InputStreamReader isr= new InputStreamReader(is);

                    int data=isr.read();
                    while (data!=-1){
                        current += (char) data;
                        data=isr.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (urlConnection!=null){
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }




        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("results");

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);

                    PopularMovie popularMovie=new PopularMovie();
                    popularMovie.setId(jsonObject1.getString("id").toString());
                    popularMovie.setImage(jsonObject1.getString("backdrop_path").toString());
                    popularMovie.setTitle(jsonObject1.getString("title").toString());
                    popularMovie.setYear(jsonObject1.getString("release_date").toString().substring(0,4));
                    popularMovie.setCompanyname(jsonObject1.getString("original_language").toString());
                    popularMovie.setVotingaverage(jsonObject1.getString("vote_average").toString());
                    popularMovies.add(popularMovie);
                }

                putDataintoAdapter();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void putDataintoAdapter() {
        movieListAdapter=new MovieListAdapter(this,popularMovies);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieListAdapter);
        movieListAdapter.notifyDataSetChanged();


    }



    public class GetData2 extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings){
            String current="";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url=new URL(strings[0]);
                    urlConnection=(HttpURLConnection) url.openConnection();
                    InputStream is=urlConnection.getInputStream();
                    InputStreamReader isr= new InputStreamReader(is);

                    int data=isr.read();
                    while (data!=-1){
                        current += (char) data;
                        data=isr.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (urlConnection!=null){
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }




        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("results");

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);

                    PopularMovie popularMovie=new PopularMovie();
                    popularMovie.setId(jsonObject1.getString("id").toString());
                    popularMovie.setImage(jsonObject1.getString("backdrop_path").toString());
                    popularMovie.setTitle(jsonObject1.getString("title").toString());
                    popularMovie.setYear(jsonObject1.getString("release_date").toString().substring(0,4));
                    popularMovie.setCompanyname(jsonObject1.getString("original_language").toString());
                    popularMovie.setVotingaverage(jsonObject1.getString("vote_average").toString());
                    playingMovies.add(popularMovie);
                }

                putDataintoAdapter2();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void putDataintoAdapter2() {
        movieListAdapter2=new MovieListAdapter2(this,playingMovies);
        recyclerView2.setHasFixedSize(true);
        layoutManager2=new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(movieListAdapter2);
        movieListAdapter2.notifyDataSetChanged();
    }


}