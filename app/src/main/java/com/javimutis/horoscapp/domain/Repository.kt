package com.javimutis.horoscapp.domain


import com.javimutis.horoscapp.domain.model.PredictionModel

// Interfaz que define qué funciones debe implementar un repositorio.
// En este caso, solo se pide una: obtener la predicción según el signo.
// La implementación concreta está en el archivo RepositoryImpl.kt (en el paquete data).
interface Repository {
    // Función suspendida que devuelve una predicción para un signo, o null si falla.
    suspend fun getPrediction(sign: String): PredictionModel?
}
