package com.example.asus.rental_app;

public class User {
    private String Nama;
    private String Password;

    public User() {
    }

    public User(String nama, String password) {
        Nama = nama;
        Password = password;
    }



    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
