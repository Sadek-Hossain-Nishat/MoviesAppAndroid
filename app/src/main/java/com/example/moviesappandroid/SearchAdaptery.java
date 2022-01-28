package com.example.moviesappandroid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SearchAdaptery extends RecyclerView.Adapter<SearchAdaptery.SearchViewHolder> {

    private Activity context;
    private ArrayList<SearchDataModel> searchmovies;


    public SearchAdaptery(Activity context, ArrayList<SearchDataModel> searchmovies) {
        this.context = context;
        this.searchmovies = searchmovies;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_searchpanel,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {


        holder.titleinsearch.setText(searchmovies.get(position).getTitle().toString());
        holder.votingaverageinsearch.setText(searchmovies.get(position).getVotingAverage().toString());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+searchmovies.get(position).getImgUrl().toString()).into(holder.searchmovieimage);




    }

    @Override
    public int getItemCount() {
        return searchmovies.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ShapeableImageView searchmovieimage;
        ImageButton watchnowmoviebtn;
        TextView titleinsearch,votingaverageinsearch;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            searchmovieimage=itemView.findViewById(R.id.imageinsearch);
            watchnowmoviebtn=itemView.findViewById(R.id.btnwatchnow);
            titleinsearch=itemView.findViewById(R.id.titleinsearch);
            votingaverageinsearch=itemView.findViewById(R.id.votinaverageinsearch);
            watchnowmoviebtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Snackbar snackbar=Snackbar.make(itemView.getRootView(),"The MOVIE WILL BE STARTED",600);
            snackbar.show();

        }
    }
}
