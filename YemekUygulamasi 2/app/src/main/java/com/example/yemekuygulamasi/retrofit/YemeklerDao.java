package com.example.yemekuygulamasi.retrofit;

import com.example.yemekuygulamasi.data.entity.SepettekiYemeklerCevap;
import com.example.yemekuygulamasi.data.entity.YemeklerCevap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface YemeklerDao {
    @GET("yemekler/tumYemekleriGetir.php")
    Call<YemeklerCevap> yemekleriYukle();

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<YemeklerCevap> sepeteEkle(@Field("yemek_adi") String yemek_adi,
                                            @Field("yemek_resim_adi") String yemek_resim_adi,
                                            @Field("yemek_fiyat") int yemek_fiyat,
                                            @Field("yemek_siparis_adet") String yemek_siparis_adet,
                                            @Field("kullanici_adi") String kullanici_adi);

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    Call<SepettekiYemeklerCevap> sil(@Field("sepet_yemek_id") int sepet_yemek_id,
                        @Field("kullanici_adi") String kullanici_adi);

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<SepettekiYemeklerCevap> sepettekiYemekleriGetir(@Field("kullanici_adi") String kullanici_adi);

}