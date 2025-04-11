package com.javimutis.horoscapp.data.network

import com.javimutis.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

// Interfaz que define cómo Retrofit hará las llamadas a la API.
interface HoroscopeApiService {

    // Este endpoint obtiene la predicción para un signo específico.
    @GET("/{sign}") // Se usa la anotación @GET con un parámetro en la URL
    suspend fun getHoroscope(@Path("sign") sign: String): PredictionResponse
}