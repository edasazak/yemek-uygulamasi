package com.example.yemekuygulamasi.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yemekuygulamasi.R;
import com.example.yemekuygulamasi.data.entity.SepettekiYemekler;
import com.example.yemekuygulamasi.databinding.SepetCardTasarimBinding;
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SepetAdapter extends RecyclerView.Adapter<SepetAdapter.CardTasarimTutucu>{
    private Context mContext;
    private List<SepettekiYemekler> sepettekiYemeklerList;
    private SepetViewModel viewModel;

    public SepetAdapter(Context mContext, List<SepettekiYemekler> sepettekiYemeklerList, SepetViewModel viewModel) {
        this.mContext = mContext;
        this.sepettekiYemeklerList = sepettekiYemeklerList;
        this.viewModel = viewModel;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private SepetCardTasarimBinding binding;
        public CardTasarimTutucu(SepetCardTasarimBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SepetCardTasarimBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.sepet_card_tasarim, parent, false);
        return new CardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        SepettekiYemekler sepettekiYemekler = sepettekiYemeklerList.get(position);
        SepetCardTasarimBinding t = holder.binding;
        t.setSepetNesnesi(sepettekiYemekler);
        resimGoster(holder, sepettekiYemekler.getYemek_resim_adi());
        t.imageViewSil.setOnClickListener(v -> {
            viewModel.sil(sepettekiYemekler.getSepet_yemek_id(), sepettekiYemekler.getKullanici_adi());
            Snackbar.make(v, sepettekiYemekler.getYemek_adi()+" sepetinizden çıkarıldı.", Snackbar.LENGTH_SHORT).show();
            Log.e("yemek","cikarilan"+sepettekiYemekler.getYemek_adi());
            if(position==0){
                sepettekiYemeklerList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeRemoved(position,sepettekiYemeklerList.size());
            }
            Log.e("yemek","cikarilan"+position);
        });



    }
    public void resimGoster(SepetAdapter.CardTasarimTutucu holder, String resimAdi){
        SepetCardTasarimBinding t = holder.binding;
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+resimAdi;
        Picasso.get().load(url).into(t.imageViewSepet);
    }

    @Override
    public int getItemCount() {
        return sepettekiYemeklerList.size();
    }
}
