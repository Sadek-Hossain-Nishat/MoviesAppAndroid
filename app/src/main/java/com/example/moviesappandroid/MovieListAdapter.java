package com.example.moviesappandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;


import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
   private Context context;
   private ArrayList<PopularMovie> popularMovies;

    public MovieListAdapter(Context context, ArrayList<PopularMovie> popularMovies) {
        this.context = context;
        this.popularMovies = popularMovies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new MovieViewHolder(LayoutInflater.from(context).inflate(
                R.layout.bigbannerhome,parent,false
        )) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

     Glide.with(context).load("https://image.tmdb.org/t/p/w500"+popularMovies.get(position).getImage()).into(holder.imageViewp);
     holder.titleview.setText(popularMovies.get(position).getTitle().toString());
     holder.yearview.setText(popularMovies.get(position).getYear().toString());
     holder.companyview.setText(popularMovies.get(position).getCompanyname().toString());
     holder.votingaverageview.setText(popularMovies.get(position).getVotingaverage().toString());

    }

    @Override
    public int getItemCount() {
        return popularMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        ShapeableImageView imageViewp;
        TextView titleview,yearview,companyview,votingaverageview;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewp=itemView.findViewById(R.id.imageViewhomebanner);
            titleview=itemView.findViewById(R.id.movietitlebanner);
            yearview=itemView.findViewById(R.id.releasedatetextviewbanner);
            companyview=itemView.findViewById(R.id.companynamebanner);
            votingaverageview=itemView.findViewById(R.id.votingaverage);

        }
    }
}
