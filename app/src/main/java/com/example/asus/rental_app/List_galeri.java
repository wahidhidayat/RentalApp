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

public class List_galeri extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference galeri;


    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Galeri, GaleriViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeri);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        galeri = database.getReference("Galeri");


        recycler_menu = (RecyclerView)findViewById(R.id.rc_galeri);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        loadGaleri();



    }

    private void loadGaleri() {
        adapter = new FirebaseRecyclerAdapter<Galeri, GaleriViewHolder>(Galeri.class, R.layout.row_galeri, GaleriViewHolder.class, galeri) {
            @Override
            protected void populateViewHolder(GaleriViewHolder viewHolder, Galeri model, int position) {
                viewHolder.rDeskripsi.setText(model.getDeskripsi());
                Picasso.get().load(model.getImage())
                        .into(viewHolder.rImage);

            }
        };
        recycler_menu.setAdapter(adapter);
    }
}