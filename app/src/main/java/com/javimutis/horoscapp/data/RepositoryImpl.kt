package com.javimutis.horoscapp.data

import android.util.Log
import com.javimutis.horoscapp.data.network.HoroscopeApiService
import com.javimutis.horoscapp.domain.Repository
import com.javimutis.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

// Implementación del repositorio que hace la llamada real a la API
class RepositoryImpl @Inject constructor(
    private val apiService: HoroscopeApiService // Inyectamos el servicio de Retrofit
) : Repository {

    // Esta función pide la predicción de un signo al servidor
    override suspend fun getPrediction(sign: String): PredictionModel? {
        // Usamos runCatching para manejar posibles errores
        runCatching {
            apiService.getHoroscope(sign) // Llamada a la API
        }
            .onSuccess { return it.toDomain() } // Si va bien, convertimos a modelo de dominio
            .onFailure {
                Log.i("MutisApp", "Ha ocurrido un error ${it.message}") // Si falla, mostramos el error
            }

        return null // Si falla, devolvemos null
    }
}