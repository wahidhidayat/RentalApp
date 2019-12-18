package com.example.asus.rental_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TransaksiViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener  {
    public TextView rNama, rAlamat,rMobil, rHarga,rLama, date, dateKembali,total;
    private ItemClickListener itemClickListener;


    public TransaksiViewHolder(@NonNull View itemView) {
        super(itemView);
        total = (TextView)itemView.findViewById(R.id.total);
        rNama = (TextView)itemView.findViewById(R.id.rNama);
        rAlamat = (TextView) itemView.findViewById(R.id.rAlamat);
        rMobil = (TextView)itemView.findViewById(R.id.rMobil);
        rHarga = (TextView)itemView.findViewById(R.id.rHarga);
        rLama = (TextView)itemView.findViewById(R.id.rLama);
        date = (TextView)itemView.findViewById(R.id.date);
        dateKembali = (TextView)itemView.findViewById(R.id.dateKembali);


        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}



