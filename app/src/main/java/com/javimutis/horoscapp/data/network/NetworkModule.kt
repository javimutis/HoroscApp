package com.javimutis.horoscapp.data.network

import com.javimutis.horoscapp.BuildConfig.BASE_URL
import com.javimutis.horoscapp.data.RepositoryImpl
import com.javimutis.horoscapp.data.core.interceptors.AuthInterceptor
import com.javimutis.horoscapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Este módulo de Dagger Hilt proporciona todo lo necesario para hacer llamadas de red
@Module
@InstallIn(SingletonComponent::class) // Hace que los objetos estén disponibles en toda la app
object NetworkModule {

    // Provee la instancia de Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // URL base de la API
            .client(okHttpClient) // Cliente HTTP que usará interceptores
            .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON a objetos Kotlin
            .build()
    }

    // Provee el cliente HTTP, con logs y el interceptor de autenticación
    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor) // Interceptor que imprime logs de la petición
            .addInterceptor(authInterceptor) // Interceptor que agrega el token
            .build()
    }

    // Provee el servicio que consume la API del horóscopo
    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopeApiService {
        return retrofit.create(HoroscopeApiService::class.java)
    }

    // Provee la implementación del repositorio
    @Provides
    fun provideRepository(apiService: HoroscopeApiService): Repository {
        return RepositoryImpl(apiService)
    }
}