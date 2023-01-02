package com.example.yemekuygulamasi.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.yemekuygulamasi.data.repo.YemeklerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetayViewModel extends ViewModel {
    private YemeklerDaoRepository yrepo;

    @Inject
    public DetayViewModel(YemeklerDaoRepository yrepo) {
        this.yrepo = yrepo;
    }
    public void sepeteEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, String yemek_siparis_adet, String kullanici_adi){
        yrepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi);
    }
    public void sil(int sepet_yemek_id, String kullanici_adi){
        yrepo.sil(sepet_yemek_id, kullanici_adi);
    }
    public void sepettekiYemekleriGetir(String kullanici_adi){
        yrepo.sepettekiYemekleriGetir(kullanici_adi);
    }
}
