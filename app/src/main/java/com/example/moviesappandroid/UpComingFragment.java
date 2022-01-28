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


public class UpComingFragment extends Fragment {

    private RecyclerView recyclerView;
    private static ArrayList<PopularMovie> upcomingmovies;

    private static String JSON_UP_COMING="https://api.themoviedb.org/3/movie/upcoming?api_key=9a1459605005a74e4827ce412d685208";

    private static MovieListAdapter movieListAdapter3;
    private static RecyclerView.LayoutManager layoutManager;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       upcomingmovies=new ArrayList<>();

        UpComingFragment.GetData4 getData=new UpComingFragment.GetData4();
        getData.execute(JSON_UP_COMING);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_up_coming, container, false);
        recyclerView=view.findViewById(R.id.recyclerViewupcoming);
        return view ;
    }




    public class GetData4 extends AsyncTask<String,String,String> {

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
                    upcomingmovies.add(popularMovie);
                }

                putDataintoAdapter();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void putDataintoAdapter() {
        movieListAdapter3=new MovieListAdapter(getActivity(),upcomingmovies);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieListAdapter3);
        movieListAdapter3.notifyDataSetChanged();


    }



public static ArrayList<PopularMovie> getMovieListupcoming(){
        return upcomingmovies;
    }








}