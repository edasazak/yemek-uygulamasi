package com.example.yemekuygulamasi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yemekuygulamasi.data.entity.Yemekler;
import com.example.yemekuygulamasi.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfaViewModel extends ViewModel {
    private YemeklerDaoRepository yrepo;
    public MutableLiveData<List<Yemekler>> yemeklerList = new MutableLiveData<>();

    @Inject
    public AnasayfaViewModel(YemeklerDaoRepository yrepo){
        this.yrepo = yrepo;
        yemekleriYukle();
        yemeklerList = yrepo.getYemeklerList();
    }
    public void yemekleriYukle() {
        yrepo.yemekleriYukle();
    }
}
