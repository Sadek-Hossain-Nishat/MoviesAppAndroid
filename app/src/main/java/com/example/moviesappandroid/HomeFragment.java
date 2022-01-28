package com.example.moviesappandroid;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.ActionBarContextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.ArrayList;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private ArrayList<PopularMovie> popularMovies,playingMovies;
    private static String JSON_URL_POPULAR="https://api.themoviedb.org/3/movie/popular?api_key=9a1459605005a74e4827ce412d685208";
    private static String JSON_URL_NOW_PLAYING="https://api.themoviedb.org/3/movie/now_playing?api_key=9a1459605005a74e4827ce412d685208";
    private static RecyclerView recyclerView,recyclerView2;
    private static MovieListAdapter movieListAdapter;
    private static MovieListAdapter2 movieListAdapter2;
    private static RecyclerView.LayoutManager layoutManager,layoutManager2;



    private ImageButton generbuttonhome,tvshowbuttonhome,goprobuttonhome,notificationiconbtn,seealliconbtn,searchbtnhome;







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        popularMovies=new ArrayList<>();
        playingMovies=new ArrayList<>();






      new GetData().execute(JSON_URL_POPULAR);

        new GetData2().execute(JSON_URL_NOW_PLAYING);








    }








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=view.findViewById(R.id.recyclerviewone);
        recyclerView2=view.findViewById(R.id.recyclerviewhome2);

        generbuttonhome=view.findViewById(R.id.genrebuttonhome);
        tvshowbuttonhome=view.findViewById(R.id.tvshowbuttonhome);
        goprobuttonhome=view.findViewById(R.id.goprobuttonhome);
        notificationiconbtn=view.findViewById(R.id.notificationiconid);
        seealliconbtn=view.findViewById(R.id.seeallid);
        searchbtnhome=view.findViewById(R.id.searchiconid);


        generbuttonhome.setOnClickListener(this);
        tvshowbuttonhome.setOnClickListener(this);
        goprobuttonhome.setOnClickListener(this);
        notificationiconbtn.setOnClickListener(this);
        seealliconbtn.setOnClickListener(this);
        searchbtnhome.setOnClickListener(this);








        return view;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.genrebuttonhome:
                Intent igenry=new Intent(getActivity(),GenereActivityHome.class);
                startActivity(igenry);
                break;
            case R.id.tvshowbuttonhome:
                Intent itvshow=new Intent(getActivity(),TvshowActivityHome.class);
                startActivity(itvshow);
                break;
            case R.id.goprobuttonhome:
                Intent igopro=new Intent(getActivity(),GoProActivityHome.class);
                startActivity(igopro);
                break;

            case R.id.notificationiconid:
                Intent inotification=new Intent(getActivity(),NotificationActivity.class);
                startActivity(inotification);
                break;

            case R.id.seeallid:
                Intent iseeall=new Intent(getActivity(),GenereActivityHome.class);
                startActivity(iseeall);
                break;

            case  R.id.searchiconid:
                Intent isearch=new Intent(getActivity(),SearchActivity.class);
                Bundle b= ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
                startActivity(isearch,b);
                break;

        }

    }




















    public class GetData extends AsyncTask<String,String,String> {

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
        movieListAdapter=new MovieListAdapter(getActivity(),popularMovies);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
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
        movieListAdapter2=new MovieListAdapter2(getActivity(),playingMovies);
        recyclerView2.setHasFixedSize(true);
        layoutManager2=new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(movieListAdapter2);
        movieListAdapter2.notifyDataSetChanged();
    }




















}