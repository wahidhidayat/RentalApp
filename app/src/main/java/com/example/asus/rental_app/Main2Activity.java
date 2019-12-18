package com.example.asus.rental_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.rental_app.Common.Common;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference car;
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Cars, MenuViewHolder> adapter;

    Cars mobil;
    Button btn_submit;
    EditText etNama;
     EditText etharga;
    EditText etNoPlat;
    EditText etImage;
    EditText etTahunProduksi;
    EditText etDetail;

    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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


        recycler_menu = (RecyclerView)findViewById(R.id.recycler_admin);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);






        loadMenu();

    }



    private void loadMenu() {

        adapter = new FirebaseRecyclerAdapter<Cars, MenuViewHolder>(Cars.class, R.layout.row_admin, MenuViewHolder.class, car) {
            @Override
            protected void populateViewHolder(final MenuViewHolder viewHolder, Cars model, int position) {
                viewHolder.nameCar.setText(model.getName());
                Picasso.get().load(model.getImage())
                        .into(viewHolder.imageCar);
                viewHolder.detail.setText(model.getDetail());
                viewHolder.harga.setText(model.getHarga());
                viewHolder.noPlat.setText(model.getNoPlat());
                viewHolder.tahunProduksi.setText(model.getTahunProduksi());
                viewHolder.carID.setText(model.getCarID());


                final Cars clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //mengambil catID dan mengirim ke FoodList class
                        Toast.makeText(Main2Activity.this, "" + clickItem.getName(), Toast.LENGTH_SHORT).show();

                        //mengambil catID dan mengirim ke FoodList class
                        Intent carList = new Intent(Main2Activity.this,Main2Activity.class);
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
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {

            FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
            Intent intent = new Intent( Main2Activity.this, Tambah.class);
            startActivity(intent);

            // Handle the camera action
        } else if (id == R.id.nav_cart) {
            FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
            Intent intent = new Intent( Main2Activity.this, List_Transaksi.class);
            startActivity(intent);

        } else if (id == R.id.nav_orders) {
            FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
            Intent intent = new Intent( Main2Activity.this, Tambah_Galeri.class);
            startActivity(intent);

        }
        else if (id == R.id.sign_outt) {
            FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
            firebaseAuth.signOut();
            Intent intent = new Intent( Main2Activity.this, Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().equals(Common.UPDATE))
        {
            showUpdateDialog(adapter.getRef(item.getOrder()).getKey(),adapter.getItem(item.getOrder()));
        }
        else if (item.getTitle().equals(Common.DETELE))
        {
            deleteCars(adapter.getRef(item.getOrder()).getKey());
        }
        return super.onContextItemSelected(item);
    }

    private void deleteCars(String key) {
        car.child(key).removeValue();
    }

    private void showUpdateDialog(final String key, final Cars item) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Main2Activity.this);
        alertDialog.setTitle("Update Cars");
        alertDialog.setMessage("Please fill full information");

        LayoutInflater inflater = this.getLayoutInflater();
        View activity_tambah = inflater.inflate(R.layout.activity_tambah, null);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        etNama = activity_tambah.findViewById(R.id.rName);
        etNoPlat = activity_tambah.findViewById(R.id.rNoPlat);
        etTahunProduksi = activity_tambah.findViewById(R.id.rTahunProduksi);
        etharga = activity_tambah.findViewById(R.id.rHarga);
        etImage = activity_tambah.findViewById(R.id.rImage);
        etDetail = activity_tambah.findViewById(R.id.rDetail);

        etNama.setText(item.getName());
        etDetail.setText(item.getDetail());
        etharga.setText(item.getHarga());
        etNoPlat.setText(item.getNoPlat());
        etImage.setText(item.getImage());
        etTahunProduksi.setText(item.getTahunProduksi());
        etNoPlat.setText(item.getNoPlat());
        alertDialog.setView(activity_tambah);
        //Set button
        alertDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                //Update information
                item.setName(etNama.getText().toString());
                item.setDetail(etDetail.getText().toString());
                item.setHarga(etharga.getText().toString());
                item.setNoPlat(etNoPlat.getText().toString());
                item.setImage(etImage.getText().toString());
                item.setTahunProduksi(etTahunProduksi.getText().toString());
                car.child(key).setValue(item);

            }
        });
        alertDialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
        //set Default Name

        //Event for button

    }


}