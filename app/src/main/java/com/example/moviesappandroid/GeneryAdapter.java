package com.example.moviesappandroid;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GeneryAdapter extends RecyclerView.Adapter<GeneryAdapter.GenryViewHolder> {


    private Activity context;
    private ArrayList<ImageBtnModel> imageBtns;


    public GeneryAdapter(Activity context, ArrayList<ImageBtnModel> imageBtns) {
        this.context = context;
        this.imageBtns = imageBtns;
    }

    @NonNull
    @Override
    public GenryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.genrybtns_list,parent,false);

        return new GenryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenryViewHolder holder, int position) {
        Glide.with(context).load(imageBtns.get(position).getBtnid())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(holder.imageButtongenry);

    }

    @Override
    public int getItemCount() {
        return imageBtns.size();
    }

    public class GenryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton imageButtongenry;
        public GenryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButtongenry=itemView.findViewById(R.id.imageButtongenry);
            imageButtongenry.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent i=new Intent(context,GenryDetailsActivity.class);
            Bundle b= ActivityOptions.makeSceneTransitionAnimation(context).toBundle();
            i.putExtra("logoid",imageBtns.get(getAdapterPosition()).getBtnid());
            i.putExtra("titleid",imageBtns.get(getAdapterPosition()).getBtntitle());
            i.putExtra("genryid",imageBtns.get(getAdapterPosition()).getGenryid());
            context.startActivity(i,b);
        }
    }
}
