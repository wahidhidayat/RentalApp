package com.example.asus.rental_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.asus.rental_app.R;
import com.example.asus.rental_app.DataModel;

import static android.text.TextUtils.isEmpty;

public class Tambah extends AppCompatActivity {

    //variable untuk refers ke Firebase Realtime Database
    private DatabaseReference database;

    //variable komponen dalam layout
    private Button btnSubmit;
    private EditText etNama;
    private EditText etharga;
    private EditText etNoPlat;
    private EditText etImage;
    private EditText etTahunProduksi;
    private EditText etDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        //inisiasi komponen dari layout
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        etNama = (EditText) findViewById(R.id.rName);
        etNoPlat = (EditText) findViewById(R.id.rNoPlat);
        etImage = (EditText) findViewById(R.id.rImage);
        etTahunProduksi = (EditText) findViewById(R.id.rTahunProduksi);
        etharga = (EditText) findViewById(R.id.rHarga);
        etDetail = (EditText) findViewById(R.id.rDetail);

        //mengambil reference database ke Firebase
        database = FirebaseDatabase.getInstance().getReference();

        final DataModel dataModel = (DataModel) getIntent().getSerializableExtra("Cars");

            //action untuk button submit
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isEmpty(etNama.getText().toString()) && !isEmpty(etImage.getText().toString()) && !isEmpty(etNoPlat.getText().toString()) && !isEmpty(etharga.getText().toString()) && !isEmpty(etTahunProduksi.getText().toString()) && !isEmpty(etDetail.getText().toString() ))
                    {
                        //ToDo nunggu method dibawah coi
                        submitDataModel(new DataModel(etNama.getText().toString(), etNoPlat.getText().toString(), etImage.getText().toString(), etharga.getText().toString(), etTahunProduksi.getText().toString(), etDetail.getText().toString()));
                    } else
                        {
                        Snackbar.make(findViewById(R.id.btn_submit), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    }
                        InputMethodManager imm = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(
                                etNama.getWindowToken(), 0);
                }
            });

    }

    private boolean isEmpty(String s)
    {
        return TextUtils.isEmpty(s);
    }


    public void submitDataModel(DataModel dataModel) {
        database.child("Cars").push().setValue(dataModel).addOnSuccessListener(this, new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid) {
                        etNama.setText("");
                        etNoPlat.setText("");
                        etImage.setText("");
                        etharga.setText("");
                        etDetail.setText("");
                        etTahunProduksi.setText("");
                        Snackbar.make(findViewById(R.id.btn_submit), "Data Berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, Tambah.class);
    }
}
