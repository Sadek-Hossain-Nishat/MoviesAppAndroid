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

public class MovieListAdapter2 extends RecyclerView.Adapter<MovieListAdapter2.MovieViewHolder> {


    private Context context;
    private ArrayList<PopularMovie> playingmovienow;

    public MovieListAdapter2(Context context, ArrayList<PopularMovie> playingmovienow) {
        this.context = context;
        this.playingmovienow = playingmovienow;
    }

    @NonNull
    @Override
    public MovieListAdapter2.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(
                R.layout.playingmovienowlayout,parent,false
        );

     return new MovieListAdapter2.MovieViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter2.MovieViewHolder holder, int position) {

        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+ playingmovienow.get(position).getImage()).into(holder.playingmoviebannerimage);
        holder.titleviewplaying.setText(playingmovienow.get(position).getTitle().toString());
        holder.companyviewplaying.setText(playingmovienow.get(position).getCompanyname().toString());


    }

    @Override
    public int getItemCount() {
        return playingmovienow.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        ShapeableImageView playingmoviebannerimage;
        TextView titleviewplaying,companyviewplaying;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            playingmoviebannerimage=itemView.findViewById(R.id.playingmoviebannerimage);
            titleviewplaying=itemView.findViewById(R.id.playingmovietitleseealllist);
            companyviewplaying=itemView.findViewById(R.id.playingmoviecompanyseealllist);

        }
    }
}
