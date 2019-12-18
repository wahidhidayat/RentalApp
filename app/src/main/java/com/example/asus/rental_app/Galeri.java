package com.example.asus.rental_app;

public class Galeri {

    private String Deskripsi, Image;

    public  Galeri(){

    }
    public Galeri(String deskripsi, String image) {
        Deskripsi = deskripsi;
        Image = image;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
