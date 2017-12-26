package com.example.root.retrofit;

import java.util.List;

/**
 * Created by root on 12/26/17.
 */

public class ResponModel {
    String kode;
    String pesan;
    List<ReadModel> result;

    public List<ReadModel> getResult() {
        return result;
    }

    public void setResult(List<ReadModel> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

}
