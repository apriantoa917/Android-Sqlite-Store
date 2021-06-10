package com.aprianto.p9;

public class Model_Barang {

    String kode,nama,satuan;

    public Model_Barang(String kode, String nama, String satuan) {
        this.nama = nama;
        this.kode = kode;
        this.satuan = satuan;
    }

    public Model_Barang() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
}
