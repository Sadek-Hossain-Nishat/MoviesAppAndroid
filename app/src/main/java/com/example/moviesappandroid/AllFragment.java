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


public class AllFragment extends Fragment {
    private RecyclerView recyclerViewsearch;
    private RecyclerView.LayoutManager layoutManagersearch;
    private SearchAdaptery adapter;
    private ArrayList<SearchDataModel> searchDataModels;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchDataModels=new ArrayList<>();

        new GetData1().execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all, container, false);
        recyclerViewsearch=view.findViewById(R.id.recyclerviewsearch);
        return view;
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

                    popularMovie.setImgUrl(jsonObject1.getString("poster_path").toString());
                    popularMovie.setTitle(jsonObject1.getString("title").toString());


                    popularMovie.setVotingAverage(jsonObject1.getString("vote_average").toString());
                    searchDataModels.add(popularMovie);
                }

                putDataintoAdapter1();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }





    @SuppressLint("NotifyDataSetChanged")
    private void putDataintoAdapter1() {
        adapter=new SearchAdaptery(getActivity(),searchDataModels);
        recyclerViewsearch.setHasFixedSize(true);

        layoutManagersearch=new LinearLayoutManager(getActivity());
        recyclerViewsearch.setLayoutManager(layoutManagersearch);
        recyclerViewsearch.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }





















}