package com.example.asus.rental_app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {

    TextView rName, rDetail, rHarga, rNoPlat, rTahunProduksi;
    ImageView rImage;
    private Button btn_pesan;
    FloatingActionButton btnCart;
    String carId = "";

    FirebaseDatabase database;
    DatabaseReference car;
    FirebaseRecyclerAdapter<Cars, MenuViewHolder> adapter;
    Cars currentCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        database = FirebaseDatabase.getInstance();
        car = database.getReference("Cars");

        rTahunProduksi = (TextView) findViewById(R.id.rTahunProduksi);
        rImage = (ImageView) findViewById(R.id.rImage);
        rName = (TextView) findViewById(R.id.rName);
        rDetail = (TextView) findViewById(R.id.rDetail);
        rHarga = (TextView) findViewById(R.id.rHarga);
        rNoPlat = (TextView) findViewById(R.id.rNoPlat);
        btn_pesan = (Button) findViewById(R.id.btn_pesan);

        if (getIntent() != null)
            carId=getIntent().getStringExtra("CarId");
        if (!carId.isEmpty())
            getDetailCar(carId);
    }

    private void getDetailCar(String carId) {
        car.child(carId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentCars = dataSnapshot.getValue(Cars.class);
                Picasso.get().load(currentCars.getImage())
                        .into(rImage);
                rName.setText(currentCars.getName());
                rHarga.setText(currentCars.getHarga());
                rDetail.setText(currentCars.getDetail());
                rNoPlat.setText(currentCars.getNoPlat());
                rTahunProduksi.setText(currentCars.getTahunProduksi());
        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, TransaksiActivity.class);
                intent.putExtra("mobil", rName.getText().toString());
                intent.putExtra("harga", rHarga.getText().toString());
                startActivity(intent);


            }
            } );
        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
