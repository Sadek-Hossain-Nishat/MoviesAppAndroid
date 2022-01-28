package com.example.moviesappandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class GenereActivityHome extends AppCompatActivity {
    private ImageView searchgenreicon;
    private RecyclerView recyclerViewgenrbtns;

    private ArrayList<ImageBtnModel> imageBtnModelArrayList;
    private GeneryAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genere_home);
        Toolbar toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchgenreicon=findViewById(R.id.imageViewgenrecardsearcicon);

        recyclerViewgenrbtns=findViewById(R.id.recyclerviewgenrebtns);



        int[] ids=new int[]{
                R.drawable.horrorbtn,
                R.drawable.actionbtn,
                R.drawable.comedybtn,
                R.drawable.dramabtn,
                R.drawable.fantasybtn,
                R.drawable.romancebtn,
                R.drawable.adventurebtn,
                R.drawable.sliceoflife,
                R.drawable.mysterybtn
        };

        String[] titles= new String[]{
                "Horror",
                "Action"
                ,"Comedy","Drama","Fantasy","Romance","Adventure",
                "Slice of Life",
                "Mystery"
        };

        int[] genreysids=new int[]{
                27,
                28,
                35,
                18,
                14,
                10749,
                12,
                99,
                9648

        };


        imageBtnModelArrayList=new ArrayList<>();

        for (int i=0;i<ids.length;i++){
            ImageBtnModel imageBtnModel=new ImageBtnModel(ids[i],titles[i],
                    genreysids[i]);

            imageBtnModelArrayList.add(imageBtnModel);
        }

        adapter=new GeneryAdapter(this,imageBtnModelArrayList);
        layoutManager=new GridLayoutManager(this,3);
        recyclerViewgenrbtns.setHasFixedSize(true);
        recyclerViewgenrbtns.setLayoutManager(layoutManager);
        recyclerViewgenrbtns.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        searchgenreicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isearch=new Intent(GenereActivityHome.this, SearchActivity.class);
                Bundle b= ActivityOptions.makeSceneTransitionAnimation(GenereActivityHome.this).toBundle();
                startActivity(isearch,b);
            }
        });








    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}