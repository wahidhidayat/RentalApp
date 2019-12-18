package com.example.asus.rental_app;

public class Transaksi {
    private  String Nama, Mobil, Harga,LamaPinjam, TanggalPinjam, TanggalKembali, Alamat, Total;
    public Transaksi(){

    }
    public Transaksi(String nama, String mobil, String harga, String lamaPinjam,String total, String tanggalPinjam, String tanggalKembali, String alamat) {
        Nama = nama;
        Mobil = mobil;
        Total = total;
        Harga = harga;
        LamaPinjam = lamaPinjam;
        TanggalPinjam = tanggalPinjam;
        TanggalKembali = tanggalKembali;
        Alamat = alamat;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
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

    public String getLamaPinjam() {
        return LamaPinjam;
    }

    public void setLamaPinjam(String lamaPinjam) {
        LamaPinjam = lamaPinjam;
    }

    public String getTanggalPinjam() {
        return TanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        TanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return TanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        TanggalKembali = tanggalKembali;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }
}
