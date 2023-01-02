package com.example.yemekuygulamasi.di;

import com.example.yemekuygulamasi.data.repo.YemeklerDaoRepository;
import com.example.yemekuygulamasi.retrofit.ApiUtils;
import com.example.yemekuygulamasi.retrofit.YemeklerDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public YemeklerDaoRepository provideYemeklerDaoRepository(YemeklerDao ydao) {
        return new YemeklerDaoRepository(ydao);
    }
    @Provides
    @Singleton
    public YemeklerDao provideYemeklerDao() {
        return ApiUtils.getYemeklerDao();
    }
}
