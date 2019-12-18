package com.example.asus.rental_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GaleriViewHolder  extends RecyclerView.ViewHolder {
   TextView  rDeskripsi;
    ImageView rImage;
    private ItemClickListener itemClickListener;


    public GaleriViewHolder(@NonNull View itemView) {
        super(itemView);
        rImage = (ImageView) itemView.findViewById(R.id.rImage);
        rDeskripsi = (TextView)itemView.findViewById(R.id.rDeskripsi);

    }



}



