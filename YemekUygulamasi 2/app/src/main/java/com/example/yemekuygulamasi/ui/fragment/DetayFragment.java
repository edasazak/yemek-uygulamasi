package com.example.yemekuygulamasi.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yemekuygulamasi.R;
import com.example.yemekuygulamasi.data.entity.SepettekiYemekler;
import com.example.yemekuygulamasi.data.entity.Yemekler;
import com.example.yemekuygulamasi.databinding.FragmentDetayBinding;
import com.example.yemekuygulamasi.ui.viewmodel.DetayViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetayFragment extends Fragment {
    private FragmentDetayBinding binding;
    private DetayViewModel viewModel;
    int count;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detay, container, false);

        binding.setDetayToolbarBaslik("Yemek Detayı");

        DetayFragmentArgs bundle = DetayFragmentArgs.fromBundle(getArguments());
        Yemekler gelenYemek = bundle.getYemek();

        SepettekiYemekler sepettekiYemekler = new SepettekiYemekler();
        binding.setYemekNesnesi(gelenYemek);
        binding.setDetayFragment(this);
        sepettekiYemekler.setKullanici_adi("eda");
        resimGoster(gelenYemek.getYemek_resim_adi());

        binding.buttonSepeteEkle.setOnClickListener(v -> {
            count += 1;
            sepettekiYemekler.getKullanici_adi();
            binding.textViewSiparisAdet.setText(String.valueOf(count));
            sepettekiYemekler.setYemek_siparis_adet(binding.textViewSiparisAdet.getText().toString());
            viewModel.sepeteEkle(gelenYemek.getYemek_adi(), gelenYemek.getYemek_resim_adi(), gelenYemek.getYemek_fiyat(), sepettekiYemekler.getYemek_siparis_adet(), sepettekiYemekler.getKullanici_adi());
        });

        binding.buttonSepettenCikar.setOnClickListener(v -> {
            if(count>0) {
                viewModel.sil(sepettekiYemekler.getSepet_yemek_id(), sepettekiYemekler.getKullanici_adi());
                count-=1;
                binding.textViewSiparisAdet.setText(String.valueOf(count));
                sepettekiYemekler.setYemek_siparis_adet(binding.textViewSiparisAdet.getText().toString());
            }else {
                Snackbar.make(v, "Sepetiniz boş", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.sepet:
                    replaceFragment(new SepetFragment());
                    break;
                case R.id.anasayfa:
                    replaceFragment(new AnasayfaFragment());
                    break;
                case R.id.profil:
                    break;
            }
            return true;
        });

        return binding.getRoot();
    }
    public void resimGoster(String resimAdi){
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+resimAdi;
        Picasso.get().load(url).into(binding.imageViewYemekDetay);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetayViewModel.class);
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.commit();
    }
}