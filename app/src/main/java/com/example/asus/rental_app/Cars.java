package com.example.asus.rental_app;



public class Cars {
    private String Name, Image, Detail, Harga, NoPlat, TahunProduksi, CarID;

    public Cars() {
    }

    public Cars(String name, String image, String detail, String harga, String noPlat, String tahunProduksi,String carID) {
        Name = name;
        Image = image;
        Detail = detail;
        Harga = harga;
        NoPlat = noPlat;
        TahunProduksi = tahunProduksi;
        CarID = carID;

    }

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
        this.Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        this.Detail = detail;
    }


    public String getHarga() { return Harga; }
    public void setHarga(String harga) {
        this.Harga = harga;
    }

    public String getNoPlat() {
        return NoPlat;
    }

    public void setNoPlat(String noPlat) {
        NoPlat = noPlat;
    }

}