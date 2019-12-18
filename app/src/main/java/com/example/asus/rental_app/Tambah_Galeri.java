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
import com.example.asus.rental_app.DataModelGaleri;

import static android.text.TextUtils.isEmpty;

public class Tambah_Galeri extends AppCompatActivity {

    //variable untuk refers ke Firebase Realtime Database
    private DatabaseReference database;

    //variable komponen dalam layout
    private Button btn_Submit;
    private EditText etDeskripsi, etImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_galeri);

        //inisiasi komponen dari layout
        btn_Submit = (Button) findViewById(R.id.btn_submit);
        etImage = (EditText) findViewById(R.id.rImage);
        etDeskripsi = (EditText) findViewById(R.id.rDeskripsi);

        //mengambil reference database ke Firebase
        database = FirebaseDatabase.getInstance().getReference();

        final DataModelGaleri dataModelGaleri = (DataModelGaleri) getIntent().getSerializableExtra("Galeri");

        //action untuk button submit
        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(etImage.getText().toString()) && !isEmpty(etDeskripsi.getText().toString()))
                {
                    submitDataModelGaleri(new DataModelGaleri(etImage.getText().toString(), etDeskripsi.getText().toString()));
                } else {
                    Snackbar.make(findViewById(R.id.btn_submit), "Data tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                }
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        etDeskripsi.getWindowToken(), 0);
            }
        });

    }
        private boolean isEmpty (String s){ return TextUtils.isEmpty(s); }


        public void submitDataModelGaleri (DataModelGaleri dataModelGaleri){
            database.child("Galeri").push().setValue(dataModelGaleri).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    etImage.setText("");
                    etDeskripsi.setText("");
                    Snackbar.make(findViewById(R.id.btn_submit), "Data Berhasil ditambahkan", Snackbar.LENGTH_LONG).show();

                }
            });

        }

        public static Intent getActIntent (Activity activity){
            // kode untuk pengambilan Intent
            return new Intent(activity, Tambah_Galeri.class);
        }
    }
