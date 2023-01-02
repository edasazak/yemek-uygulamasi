package com.example.yemekuygulamasi.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SepettekiYemeklerCevap {
    @SerializedName("sepet_yemekler")
    private List<SepettekiYemekler> sepet_yemekler;
    @SerializedName("success")
    private int success;
    @SerializedName("message")
    private String message;

    public SepettekiYemeklerCevap() {
    }

    public SepettekiYemeklerCevap(List<SepettekiYemekler> sepet_yemekler, int success, String message) {
        this.sepet_yemekler = sepet_yemekler;
        this.success = success;
        this.message = message;
    }

    public List<SepettekiYemekler> getSepet_yemekler() {
        return sepet_yemekler;
    }

    public void setSepet_yemekler(List<SepettekiYemekler> sepet_yemekler) {
        this.sepet_yemekler = sepet_yemekler;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
