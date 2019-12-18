package com.example.asus.rental_app;

import java.io.Serializable;

public class DataModelGaleri implements Serializable {
    private String Deskripsi, Image;

    public DataModelGaleri(String deskripsi, String image) {
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

    @Override
    public String toString() {
        return " " +Deskripsi+ "\n" +
                " " +Image+ "\n" ;
    }

}
