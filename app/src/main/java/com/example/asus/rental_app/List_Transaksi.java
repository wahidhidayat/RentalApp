package com.example.asus.rental_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class List_Transaksi extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference transaksi;


    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Transaksi, TransaksiViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_transaksi);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        transaksi = database.getReference("Transaksi");


        recycler_menu = (RecyclerView)findViewById(R.id.recycler_transaksi);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        loadTransaksi();



    }

    private void loadTransaksi() {
        adapter = new FirebaseRecyclerAdapter<Transaksi, TransaksiViewHolder>(Transaksi.class, R.layout.row_transaksi, TransaksiViewHolder.class, transaksi) {
            @Override
            protected void populateViewHolder(TransaksiViewHolder viewHolder, Transaksi model, int position) {
                viewHolder.rNama.setText(model.getNama());
                viewHolder.rAlamat.setText(model.getAlamat());
                viewHolder.rMobil.setText(model.getMobil());
                viewHolder.rHarga.setText(model.getHarga());
                viewHolder.rLama.setText(model.getLamaPinjam());
                viewHolder.date.setText(model.getTanggalPinjam());
                viewHolder.dateKembali.setText(model.getTanggalKembali());
                viewHolder.total.setText(model.getTotal());


            }
        };
        recycler_menu.setAdapter(adapter);
    }
}