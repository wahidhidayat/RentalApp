package com.example.asus.rental_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.rental_app.Common.Common;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener  {
    public TextView nameCar, detail,harga,noPlat, tahunProduksi, carID;
    public ImageView imageCar;
    private ItemClickListener itemClickListener;


    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        nameCar = (TextView)itemView.findViewById(R.id.rName);
        imageCar = (ImageView)itemView.findViewById(R.id.rImage);
        detail = (TextView)itemView.findViewById(R.id.rDetail);
        harga = (TextView)itemView.findViewById(R.id.rHarga);
        noPlat = (TextView)itemView.findViewById(R.id.rNoPlat);
        tahunProduksi = (TextView)itemView.findViewById(R.id.rTahunProduksi);
        carID = (TextView)itemView.findViewById(R.id.rKey);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        contextMenu.setHeaderTitle("Select the action");

        contextMenu.add(0,0,getAdapterPosition(), Common.UPDATE);
        contextMenu.add(0,0,getAdapterPosition(),Common.DETELE);

    }
}


