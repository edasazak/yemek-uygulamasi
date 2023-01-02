package com.example.yemekuygulamasi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yemekuygulamasi.R;
import com.example.yemekuygulamasi.data.entity.Yemekler;
import com.example.yemekuygulamasi.databinding.ListeCardTasarimBinding;
import com.example.yemekuygulamasi.ui.fragment.AnasayfaFragmentDirections;
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YemeklerAdapter extends RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>{
    private Context mContext;
    private List<Yemekler> yemeklerList;
    private AnasayfaViewModel viewModel;

    public YemeklerAdapter(Context mContext, List<Yemekler> yemeklerList, AnasayfaViewModel viewModel) {
        this.mContext = mContext;
        this.yemeklerList = yemeklerList;
        this.viewModel = viewModel;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private ListeCardTasarimBinding binding;
        public CardTasarimTutucu(ListeCardTasarimBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListeCardTasarimBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.liste_card_tasarim, parent, false);
        return new CardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Yemekler yemekler = yemeklerList.get(position);
        ListeCardTasarimBinding t = holder.binding;
        t.setYemekNesnesi(yemekler);
        t.listeCard.setOnClickListener(v -> {
            AnasayfaFragmentDirections.DetayGecis gecis = AnasayfaFragmentDirections.detayGecis(yemekler);
            Navigation.findNavController(v).navigate(gecis);
        });
        resimGoster(holder, yemekler.getYemek_resim_adi());
    }

    public void resimGoster(CardTasarimTutucu holder, String resimAdi){
        ListeCardTasarimBinding t = holder.binding;
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+resimAdi;
        Picasso.get().load(url).into(t.imageViewListe);
    }
    @Override
    public int getItemCount() {
        return yemeklerList.size();
    }
}
