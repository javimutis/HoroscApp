package com.javimutis.horoscapp.data.network

import com.javimutis.horoscapp.data.RepositoryImpl
import com.javimutis.horoscapp.data.providers.network.HoroscopeApiService
import com.javimutis.horoscapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://newastro.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopeApiService {
        return retrofit.create(HoroscopeApiService::class.java)
    }
    @Provides
    fun provideRepository(apiService: HoroscopeApiService): Repository {
        return RepositoryImpl(apiService)
    }
}