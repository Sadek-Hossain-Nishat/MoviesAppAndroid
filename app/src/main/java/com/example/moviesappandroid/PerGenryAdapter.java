package com.example.moviesappandroid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;

public class PerGenryAdapter  extends RecyclerView.Adapter<PerGenryAdapter.PerGenryViewHolder> {

    private Activity context;
    private ArrayList<GenryMovieModel> genryMovieModels;


    public PerGenryAdapter(Activity context, ArrayList<GenryMovieModel> genryMovieModels) {
        this.context = context;
        this.genryMovieModels = genryMovieModels;
    }

    @NonNull
    @Override
    public PerGenryAdapter.PerGenryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pergenry_view_list,parent,false);
        return new PerGenryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerGenryAdapter.PerGenryViewHolder holder, int position) {

        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+genryMovieModels.get(position).getPergenryimgurl().toString())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(holder.imageViewpergenry);
        holder.pergenrytitle.setText(genryMovieModels.get(position).getTitlepergenry().toString());

    }

    @Override
    public int getItemCount() {
        return genryMovieModels.size();
    }


    public class PerGenryViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView imageViewpergenry;
        TextView pergenrytitle;
        public PerGenryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewpergenry=itemView.findViewById(R.id.imageViewpergenry);
            pergenrytitle=itemView.findViewById(R.id.pergenrytitle);

        }
    }




}

