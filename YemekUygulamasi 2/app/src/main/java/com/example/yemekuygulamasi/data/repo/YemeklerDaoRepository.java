package com.example.yemekuygulamasi.data.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.yemekuygulamasi.data.entity.SepettekiYemekler;
import com.example.yemekuygulamasi.data.entity.SepettekiYemeklerCevap;
import com.example.yemekuygulamasi.data.entity.Yemekler;
import com.example.yemekuygulamasi.data.entity.YemeklerCevap;
import com.example.yemekuygulamasi.retrofit.YemeklerDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
    private MutableLiveData<List<Yemekler>> yemeklerList;
    private MutableLiveData<List<SepettekiYemekler>> sepettekiYemeklerList = null;
    private YemeklerDao ydao;
    public YemeklerDaoRepository(YemeklerDao ydao) {
        this.ydao = ydao;
        yemeklerList = new MutableLiveData();
        sepettekiYemeklerList = new MutableLiveData();
    }
    public MutableLiveData<List<Yemekler>> getYemeklerList() {
        return yemeklerList;
    }
    public MutableLiveData<List<SepettekiYemekler>> getSepettekiYemeklerList() {
        return sepettekiYemeklerList;
    }
    public void yemekleriYukle() {
        ydao.yemekleriYukle().enqueue(new Callback<YemeklerCevap>() {
            @Override
            public void onResponse(Call<YemeklerCevap> call, Response<YemeklerCevap> response) {
                List<Yemekler> list = response.body().getYemekler();
                yemeklerList.setValue(list);
            }

            @Override
            public void onFailure(Call<YemeklerCevap> call, Throwable t) {}
        });
    }
    public void sepeteEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, String yemek_siparis_adet, String kullanici_adi) {
        ydao.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(new Callback<YemeklerCevap>() {
            @Override
            public void onResponse(Call<YemeklerCevap> call, Response<YemeklerCevap> response) {
                //Log.e("yemek","res"+response);
            }

            @Override
            public void onFailure(Call<YemeklerCevap> call, Throwable t) {

            }
        });
    }
    public void sil(int sepet_yemek_id, String kullanici_adi){
        ydao.sil(sepet_yemek_id, kullanici_adi).enqueue(new Callback<SepettekiYemeklerCevap>() {
            @Override
            public void onResponse(Call<SepettekiYemeklerCevap> call, Response<SepettekiYemeklerCevap> response) {
                sepettekiYemekleriGetir(kullanici_adi);
                Log.e("yemek","res"+response);
            }

            @Override
            public void onFailure(Call<SepettekiYemeklerCevap> call, Throwable t) {

            }
        });
    }
    public void sepettekiYemekleriGetir(String kullanici_adi) {
        ydao.sepettekiYemekleriGetir(kullanici_adi).enqueue(new Callback<SepettekiYemeklerCevap>() {
            @Override
            public void onResponse(Call<SepettekiYemeklerCevap> call, Response<SepettekiYemeklerCevap> response) {
                    List<SepettekiYemekler> liste = response.body().getSepet_yemekler();
                    sepettekiYemeklerList.setValue(liste);
                    Log.e("yemek","res"+response);

            }

            @Override
            public void onFailure(Call<SepettekiYemeklerCevap> call, Throwable t) {

            }
        });
    }
}
