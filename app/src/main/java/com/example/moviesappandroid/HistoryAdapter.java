package com.example.moviesappandroid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private Activity context;
    private ArrayList<SearchDataModel> searchDataModels;


    public HistoryAdapter(Activity context, ArrayList<SearchDataModel> searchDataModels) {
        this.context = context;
        this.searchDataModels = searchDataModels;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_history_discovery,parent,false);

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {

        holder.titleinhistory.setText(searchDataModels.get(position).getTitle());
        holder.votingaverageinhistory.setText(searchDataModels.get(position).getVotingAverage());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+searchDataModels.get(position).getImgUrl()).into(holder.movieimageinhistory);

    }

    @Override
    public int getItemCount() {
        return searchDataModels.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ShapeableImageView movieimageinhistory;
        ImageButton candelbtnhistroy;
        TextView titleinhistory,votingaverageinhistory;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            movieimageinhistory=itemView.findViewById(R.id.movimaghistory);
            titleinhistory=itemView.findViewById(R.id.historymovietitle);
            votingaverageinhistory=itemView.findViewById(R.id.votingaveragehistory);
            candelbtnhistroy=itemView.findViewById(R.id.candcelbtninhistory);
            candelbtnhistroy.setOnClickListener(this);




        }

        @Override
        public void onClick(View v) {


        }
    }
}
