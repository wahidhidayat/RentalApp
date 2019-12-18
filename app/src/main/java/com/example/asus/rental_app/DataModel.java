package com.example.asus.rental_app;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class DataModel implements Serializable{

    private String TahunProduksi, Name, Image, Detail, Harga, NoPlat, CarID;

    public String getCarID() {
        return CarID;
    }

    public void setCarID(String carID) {
        CarID = carID;
    }

    public String getTahunProduksi() {
        return TahunProduksi;
    }

    public void setTahunProduksi(String tahunProduksi) {
        TahunProduksi = tahunProduksi;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getNoPlat() {
        return NoPlat;
    }

    public void setNoPlat(String noPlat) {
        NoPlat = noPlat;
    }

    @Override
    public String toString() {
        return " " +Name+ "\n" +
                " " +NoPlat+ "\n" +
                " " +Image+ "\n" +
                " " +TahunProduksi+ "\n" +
                " " +Detail+ "\n" +
                " " +Harga+ "\n";
    }

    public DataModel(String tahunProduksi, String name, String image, String detail, String harga, String noPlat) {
        TahunProduksi = tahunProduksi;
        Name = name;
        Image = image;
        Detail = detail;
        Harga = harga;
        NoPlat = noPlat;
    }
}
