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

import java.util.ArrayList;

public class MovieListAdapter3 extends RecyclerView.Adapter<MovieListAdapter3.MovieViewHolder> {


    private Context context;
    private ArrayList<PopularMovie> playingmovienow;

    public MovieListAdapter3(Context context, ArrayList<PopularMovie> playingmovienow) {
        this.context = context;
        this.playingmovienow = playingmovienow;
    }

    @NonNull
    @Override
    public MovieListAdapter3.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new MovieListAdapter3.MovieViewHolder(LayoutInflater.from(context).inflate(
                R.layout.seealllist,parent,false
        )) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter3.MovieViewHolder holder, int position) {


        holder.titleviewplaying.setText(playingmovienow.get(position).getTitle().toString());
        holder.companyviewplaying.setText(playingmovienow.get(position).getCompanyname().toString());


    }

    @Override
    public int getItemCount() {
        return playingmovienow.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{


        TextView titleviewplaying,companyviewplaying;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);


            titleviewplaying=itemView.findViewById(R.id.playingmovietitleseealllist2);
            companyviewplaying=itemView.findViewById(R.id.playingmoviecompanyseealllist2);

        }
    }
}
