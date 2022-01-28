package com.example.moviesappandroid;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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


public class DiscoveryFragment extends Fragment {

    ImageView searchViewdiscovery;
    private RecyclerView recyclerViewhistory,recyclerViewdiscovery;
    private RecyclerView.LayoutManager layoutManagerhistory,layoutManagerdiscovery;
    private ArrayList<SearchDataModel> searchDataModels;
    private ArrayList<GenryMovieModel> genryMovieModels;
    private DiscoveryAdapter discoveryAdapter;
    private HistoryAdapter historyAdapter;









    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater1=TransitionInflater.from(getActivity());
        setExitTransition(inflater1.inflateTransition(R.transition.trans_anim));

        searchDataModels=new ArrayList<>();
        genryMovieModels=new ArrayList<>();



        new GetData1().execute();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_discovery, container, false);
        searchViewdiscovery=view.findViewById(R.id.searcviewdiscovery);

        recyclerViewhistory=view.findViewById(R.id.recyclerviewhistory);
        recyclerViewdiscovery=view.findViewById(R.id.recyclerviewpopularhome);



        searchViewdiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_layout,new SearchFragment()).commit();

            }
        });

        return  view;
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

                    SearchDataModel popularMovie=new SearchDataModel();
                    GenryMovieModel genryMovieModel=new GenryMovieModel();

                    popularMovie.setImgUrl(jsonObject1.getString("poster_path").toString());
                    genryMovieModel.setPergenryimgurl(jsonObject1.getString("poster_path").toString());
                    popularMovie.setTitle(jsonObject1.getString("title").toString());
                    genryMovieModel.setTitlepergenry(jsonObject1.getString("title").toString());


                    popularMovie.setVotingAverage(jsonObject1.getString("vote_average").toString());
                    searchDataModels.add(popularMovie);
                    genryMovieModels.add(genryMovieModel);
                }

                putDataintoAdapter1();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }





    @SuppressLint("NotifyDataSetChanged")
    private void putDataintoAdapter1() {
        historyAdapter=new HistoryAdapter(getActivity(),searchDataModels);
        discoveryAdapter=new DiscoveryAdapter(getActivity(),genryMovieModels);

        recyclerViewhistory.setHasFixedSize(true);
        recyclerViewdiscovery.setHasFixedSize(true);
        layoutManagerhistory=new LinearLayoutManager(getActivity());
        layoutManagerdiscovery=new GridLayoutManager(getActivity(),2);


       recyclerViewhistory.setLayoutManager(layoutManagerhistory);
       recyclerViewdiscovery.setLayoutManager(layoutManagerdiscovery);
       recyclerViewhistory.setAdapter(historyAdapter);
       recyclerViewdiscovery.setAdapter(discoveryAdapter);

       historyAdapter.notifyDataSetChanged();
       discoveryAdapter.notifyDataSetChanged();


    }


























































}