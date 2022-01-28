package com.example.moviesappandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

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

public class GenryDetailsActivity extends AppCompatActivity {

    private ImageView genrylogoview;
    private TextView gentrytitleview,genrydescriptionview;

    private int genryid;
    private String JSON_GENRY_URL;
    private ArrayList<GenryMovieModel> genryMovieModels;

    private RecyclerView recyclerViewpergenry;
    private RecyclerView.LayoutManager layoutManagerpergenry;
    private PerGenryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genry_details);
        Toolbar toolbar=findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        genrylogoview=findViewById(R.id.genryimageid);
        gentrytitleview=findViewById(R.id.genrytitlid);
        genrydescriptionview=findViewById(R.id.genrydescriptionid);

        Intent iget=getIntent();
        int imgid=iget.getIntExtra("logoid",0);
        Glide.with(this).load(imgid)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(genrylogoview);

        String titleid=iget.getStringExtra("titleid");
        gentrytitleview.setText(titleid.toString());
        genryMovieModels=new ArrayList<>();

        recyclerViewpergenry=findViewById(R.id.recyclerviewgenrydetailsid);



        genryid=iget.getIntExtra("genryid",0);
        JSON_GENRY_URL="https://api.themoviedb.org/3/list/"+genryid+"?api_key=9a1459605005a74e4827ce412d685208";


        new GetData3().execute();






    }




    public class GetData3 extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings){
            String current="";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url=new URL(JSON_GENRY_URL);
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
                genrydescriptionview.setText(jsonObject.getString("description").toString());
                JSONArray jsonArray=jsonObject.getJSONArray("items");

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);

                    GenryMovieModel genryMovieModel=new GenryMovieModel();

                    genryMovieModel.setPergenryimgurl(jsonObject1.getString("poster_path").toString());
                    genryMovieModel.setTitlepergenry(jsonObject1.getString("title").toString());
                    genryMovieModels.add(genryMovieModel);

                }

                putDataintoAdapter3();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void putDataintoAdapter3() {

        adapter=new PerGenryAdapter(this,genryMovieModels);
        layoutManagerpergenry=new GridLayoutManager(this,2);
        recyclerViewpergenry.setHasFixedSize(true);
        recyclerViewpergenry.setLayoutManager(layoutManagerpergenry);
        recyclerViewpergenry.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}