package com.example.yemekuygulamasi.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yemekuygulamasi.R;
import com.example.yemekuygulamasi.databinding.FragmentSepetBinding;
import com.example.yemekuygulamasi.ui.adapter.SepetAdapter;
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SepetFragment extends Fragment {
    private FragmentSepetBinding binding;
    private SepetViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false);

        binding.setSepetToolbarBaslik("Sepetiniz");
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbarSepet);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.sepet:
                    replaceFragment(binding.getSepetFragment());
                    break;
                case R.id.anasayfa:
                    replaceFragment(new AnasayfaFragment());
                    break;
                case R.id.profil:
                    break;
            }
            return true;
        });

        viewModel.sepetteki.observe(getViewLifecycleOwner(), sepetteki -> {
            SepetAdapter adapter = new SepetAdapter(requireContext(), sepetteki, viewModel);
            binding.setSepetAdapter(adapter);
        });


        binding.setSepetFragment(this);
        return binding.getRoot();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SepetViewModel.class);
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.commit();
    }
}