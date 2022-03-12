package com.christian_magana.rickAndMortyMVVM.di

import com.christian_magana.rickAndMortyMVVM.common.Constants
import com.christian_magana.rickAndMortyMVVM.data.remote.RickAndMortyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return  Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_RAM_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideRickAndMortyApiService(retrofit: Retrofit): RickAndMortyApiService{
        return retrofit.create(RickAndMortyApiService::class.java)
    }

}