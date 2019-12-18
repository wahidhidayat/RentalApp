package com.example.asus.rental_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textFullName;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference car;



    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Cars, MenuViewHolder> adapter;
    private List<Cars> mCars = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        car = database.getReference("Cars");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        textFullName = (TextView)headerView.findViewById(R.id.textFullName);
        textFullName.setText(com.example.asus.rental_app.Common.Common.currentUser.getNama());



        recycler_menu = (RecyclerView)findViewById(R.id.recycler_car);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);






        loadMenu();

    }




    private void loadMenu() {

        adapter = new FirebaseRecyclerAdapter<Cars, MenuViewHolder>(Cars.class, R.layout.row, MenuViewHolder.class, car) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Cars model, int position) {
                viewHolder.nameCar.setText(model.getName());
                Picasso.get().load(model.getImage())
                        .into(viewHolder.imageCar);
                final Cars clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(MainActivity.this, "" + clickItem.getName(), Toast.LENGTH_SHORT).show();

                        //mengambil catID dan mengirim ke FoodList class
                        Intent carList = new Intent(MainActivity.this, DetailActivity.class);
                        carList.putExtra("CarId", adapter.getRef(position).getKey());
                        startActivity(carList);

                    }
                });


            }

        };
        recycler_menu.setAdapter(adapter);

    }

                @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();




        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
            Intent intent = new Intent( MainActivity.this, List_galeri.class);
            startActivity(intent);

            // Handle the camera action
        } else if (id == R.id.nav_cart) {
            FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
            Intent intent = new Intent( MainActivity.this, List_Transaksi.class);
            startActivity(intent);

        } else if (id == R.id.nav_orders) {
            FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
            Intent intent = new Intent( MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.sign_outt) {
            FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
            firebaseAuth.signOut();
            Intent intent = new Intent( MainActivity.this, Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
