package com.example.moviesappandroid;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class SeeAllFragment extends Fragment {
    private static RecyclerView recyclerViewone,recyclerViewtwo,recyclerViewthree;

    private static MovieListAdapter3 movieListAdapter1,movieListAdapter2,movieListAdapter3;
    private static RecyclerView.LayoutManager layoutManager1,layoutManager2,layoutManager3;
    private ArrayList<PopularMovie> latestmovies,topratedmovies,upcomingmovies;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        latestmovies=new ArrayList<>();
        topratedmovies=new ArrayList<>();
        upcomingmovies=new ArrayList<>();



        new GetData1().execute();

        new GetData2().execute();


        new GetData3().execute();









    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view= inflater.inflate(R.layout.fragment_see_all, container, false);

        recyclerViewone=view.findViewById(R.id.recyclerViewtopratedseealllist);
        recyclerViewtwo=view.findViewById(R.id.recyclerViewlatestseeall);
        recyclerViewthree=view.findViewById(R.id.recyclerViewupcomingseealllist);













        return view ;
    }



    public class GetData1 extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings){
            String current="";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url=new URL("https://api.themoviedb.org/3/movie/top_rated?api_key=9a1459605005a74e4827ce412d685208");
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
                    latestmovies.add(popularMovie);
                }

                putDataintoAdapter1();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }





    @SuppressLint("NotifyDataSetChanged")
    private void putDataintoAdapter1() {
        movieListAdapter1=new MovieListAdapter3(getActivity(),latestmovies);
        recyclerViewone.setHasFixedSize(true);

        layoutManager1=new LinearLayoutManager(getActivity());
        recyclerViewone.setLayoutManager(layoutManager1);
        recyclerViewone.setAdapter(movieListAdapter1);
        movieListAdapter1.notifyDataSetChanged();


    }



    public class GetData2 extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings){
            String current="";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url=new URL("https://api.themoviedb.org/3/movie/top_rated?api_key=9a1459605005a74e4827ce412d685208");
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
                    topratedmovies.add(popularMovie);
                }

                putDataintoAdapter2();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void putDataintoAdapter2() {
        movieListAdapter2=new MovieListAdapter3(getActivity(),topratedmovies);
        recyclerViewtwo.setHasFixedSize(true);
        layoutManager2=new LinearLayoutManager(getActivity());
        recyclerViewtwo.setLayoutManager(layoutManager2);
        recyclerViewtwo.setAdapter(movieListAdapter2);
        movieListAdapter2.notifyDataSetChanged();


    }










    public class GetData3 extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings){
            String current="";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url=new URL("https://api.themoviedb.org/3/movie/upcoming?api_key=9a1459605005a74e4827ce412d685208");
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
                    upcomingmovies.add(popularMovie);
                }

                putDataintoAdapter3();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void putDataintoAdapter3() {
        movieListAdapter3=new MovieListAdapter3(getActivity(),upcomingmovies);
        recyclerViewthree.setHasFixedSize(true);
        layoutManager3=new LinearLayoutManager(getActivity());
        recyclerViewthree.setLayoutManager(layoutManager3);
        recyclerViewthree.setAdapter(movieListAdapter3);
        movieListAdapter3.notifyDataSetChanged();


    }










}