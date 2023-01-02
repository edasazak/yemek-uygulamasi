package com.example.yemekuygulamasi.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.yemekuygulamasi.R;
import com.example.yemekuygulamasi.databinding.FragmentAnasayfaBinding;
import com.example.yemekuygulamasi.ui.adapter.YemeklerAdapter;
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnasayfaFragment extends Fragment {
    private FragmentAnasayfaBinding binding;
    private AnasayfaViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false);

        binding.setAnasayfaToolbarBaslik("Yemekler");
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbarAnasayfa);

        binding.rvListe.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.sepet:
                    replaceFragment(new SepetFragment());
                    break;
                case R.id.anasayfa:
                    replaceFragment(binding.getAnasayfaFragment());
                    break;
                case R.id.profil:
                    break;
            }
            return true;
        });


        viewModel.yemeklerList.observe(getViewLifecycleOwner(), yemeklerList -> {
            YemeklerAdapter adapter = new YemeklerAdapter(requireContext(), yemeklerList, viewModel);
            binding.setYemeklerAdapter(adapter);
        });



        binding.setAnasayfaFragment(this);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnasayfaViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.yemekleriYukle();
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.commit();
    }
}