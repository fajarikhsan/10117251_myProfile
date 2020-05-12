package com.example.myprofile;

//Tanggal pengerjaan : 12-10-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import androidx.annotation.NonNull;

public class Model {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String nim, nama, kelas, telepon, email, insta;

    public Model () {

    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    @Override
    public String toString() {
        return  "Nim            : "+nim+"\n" +
                "Nama         : "+nama+"\n" +
                "Kelas          : "+kelas+"\n" +
                "Telepon      : "+telepon+"\n" +
                "Email          : "+email+"\n" +
                "Instagram  : @"+insta;
    }
}
