package com.javimutis.horoscapp.data

import android.util.Log
import com.javimutis.horoscapp.data.providers.network.HoroscopeApiService
import com.javimutis.horoscapp.domain.Repository
import com.javimutis.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

//Llamada a Retrofit
class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("MutisApp", "Ha ocurrido un error ${it.message}") }
        return null
    }
}