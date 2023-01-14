package com.example.yemekuygulamasi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yemekuygulamasi.data.entity.SepettekiYemekler;
import com.example.yemekuygulamasi.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SepetViewModel extends ViewModel {
    private YemeklerDaoRepository yrepo;
    public MutableLiveData<List<SepettekiYemekler>> sepetteki = new MutableLiveData<>();

    @Inject
    public SepetViewModel(YemeklerDaoRepository yrepo){
        this.yrepo = yrepo;
        sepetteki = yrepo.getSepettekiYemeklerList();
    }
    public void sil(int sepet_yemek_id, String kullanici_adi){
        yrepo.sil(sepet_yemek_id, kullanici_adi);
    }
}
