package com.example.asus.rental_app;

import java.io.Serializable;

public class ModelTransaksi implements Serializable {
    private String Nama, Mobil, Harga,LamaPinjam, TanggalPinjam, TanggalKembali, Alamat, Total;


    public ModelTransaksi(String nama,String total, String mobil, String harga,String lamaPinjam, String tanggalPinjam, String tanggalKembali, String alamat) {
        Nama = nama;
        Mobil = mobil;
        Harga = harga;
        this.TanggalPinjam = tanggalPinjam;
        this.TanggalKembali = tanggalKembali;
        this.LamaPinjam = lamaPinjam;
        Alamat = alamat;
        Total = total;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getLamaPinjam() {
        return LamaPinjam;
    }

    public void setLamaPinjam(String lamaPinjam) {
        LamaPinjam = lamaPinjam;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getMobil() {
        return Mobil;
    }

    public void setMobil(String mobil) {
        Mobil = mobil;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getTanggalPinjam() {
        return TanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalpinjam) {
        this.TanggalPinjam = tanggalpinjam;
    }

    public String getTanggalKembali() {
        return TanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.TanggalKembali = tanggalKembali;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }
    public String toString() {
        return " " +Nama+ "\n" +
                " " +Mobil+ "\n" +
                " " +Harga+ "\n" +
                " " +LamaPinjam+ "\n" +
                " " +TanggalPinjam+ "\n" +
                " " +TanggalKembali+ "\n" +
                " " +Total+ "\n" +
                " " +Alamat+ "\n";
    }

}
