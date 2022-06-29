package com.example.tugasakhirgan;

public class RiwayatModel {
    String id, id_jasa, tanggal, tagihan, status;

    public RiwayatModel(){
    }

    public RiwayatModel(String id, String id_jasa, String tanggal, String tagihan, String status) {
        this.id = id;
        this.id_jasa = id_jasa;
        this.tanggal = tanggal;
        this.tagihan = tagihan;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_jasa() {
        return id_jasa;
    }

    public void setId_jasa(String id_jasa) {
        this.id_jasa = id_jasa;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTagihan() {
        return tagihan;
    }

    public void setTagihan(String tagihan) {
        this.tagihan = tagihan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
