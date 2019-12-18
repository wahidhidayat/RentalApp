package com.example.asus.rental_app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import static android.text.TextUtils.isEmpty;

public class TransaksiActivity extends AppCompatActivity {

    private Button buttonpinjam;
    Button buttonPinjam;
    EditText date, dateKembali, rLama, rNama, rMobil, rHarga,rAlamat, total;
    DatePickerDialog datePickerDialog;
    FirebaseDatabase database;
    private DatabaseReference car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        //Todo 8 lengkapi code sehingga sesuai dengan requirement

        date = (EditText) findViewById(R.id.date);

        buttonPinjam=(Button)findViewById(R.id.buttonPinjam);
        rNama = (EditText)findViewById(R.id.rNama);
        rMobil = (EditText)findViewById(R.id.rMobil);
        Intent intent = getIntent();
        String mobil = intent.getStringExtra("mobil");
        rMobil.setText(mobil);
        rHarga =(EditText)findViewById(R.id.rHarga);
        String harga = intent.getStringExtra("harga");
        rHarga.setText(harga);
        rLama = (EditText)findViewById(R.id.rLama);
        rAlamat =(EditText)findViewById(R.id.rAlamat);
        total = (EditText)findViewById(R.id.total);

        dateKembali = (EditText) findViewById(R.id.dateKembali);


        dateKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(TransaksiActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dateKembali.setText(year + "-"
                                        + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        date = (EditText) findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(TransaksiActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(year + "-"
                                        + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        car = FirebaseDatabase.getInstance().getReference();

        final ModelTransaksi modelTransaksi = (ModelTransaksi) getIntent().getSerializableExtra("Transaksi");
        buttonPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int total1 = Integer.parseInt(rHarga.getText().toString()) * Integer.parseInt(rLama.getText().toString());
                total.setText(String.valueOf(total1));
                if (!isEmpty(rNama.getText().toString()) &&!isEmpty(total.getText().toString())&& !isEmpty(rAlamat.getText().toString()) && !isEmpty(rMobil.getText().toString()) && !isEmpty(rHarga.getText().toString()) && !isEmpty(rLama.getText().toString()) && !isEmpty(date.getText().toString() ) && !isEmpty(dateKembali.getText().toString() ))
                {
                    submitModelTransaksi(new ModelTransaksi(rNama.getText().toString(), total.getText().toString(), rAlamat.getText().toString(), rMobil.getText().toString(), rHarga.getText().toString(), rLama.getText().toString(), date.getText().toString(),dateKembali.getText().toString()));
                } else
                {
                    Snackbar.make(findViewById(R.id.buttonPinjam), "Data Transaksi tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                }
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        rNama.getWindowToken(), 0);
            }
        });

    }

    private boolean isEmpty(String s)
    {
        return TextUtils.isEmpty(s);
    }


    public void submitModelTransaksi(ModelTransaksi modelTransaksi) {
        car.child("Transaksi").push().setValue(modelTransaksi).addOnSuccessListener(this, new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid) {
                rNama.setText("");
                rAlamat.setText("");
                rMobil.setText("");
                rHarga.setText("");
                rLama.setText("");
                date.setText("");
                dateKembali.setText("");
                total.setText("");
                Snackbar.make(findViewById(R.id.buttonPinjam), "Transaksi Berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }





}

