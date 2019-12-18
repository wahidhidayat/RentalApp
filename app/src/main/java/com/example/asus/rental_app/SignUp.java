package com.example.asus.rental_app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    EditText edtPhone, edtName, edtPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = (EditText)findViewById(R.id.edtName);
        edtPhone = (EditText)findViewById(R.id.edtPhone);
        edtPassword = (EditText)findViewById(R.id.edtPassword);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference akun_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please Wait..");
                mDialog.show();

                akun_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {
                       if(dataSnapshot.child(edtPhone.getText().toString()).exists())
                       {
                           mDialog.dismiss();
                           Toast.makeText(SignUp.this, "Username Already Register", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           mDialog.dismiss();
                           User user = new User(edtName.getText().toString(),edtPassword.getText().toString());
                           akun_user.child(edtPhone.getText().toString()).setValue(user);
                           Toast.makeText(SignUp.this, "Register Success", Toast.LENGTH_SHORT).show();
                           finish();
                       }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
