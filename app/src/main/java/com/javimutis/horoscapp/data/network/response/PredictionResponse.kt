package com.javimutis.horoscapp.data.providers.network.response

import com.google.gson.annotations.SerializedName
import com.javimutis.horoscapp.domain.model.PredictionModel


// Esta clase representa cómo viene la predicción del horóscopo desde el servidor.
data class PredictionResponse(
    @SerializedName("date") val date: String, // Fecha de la predicción
    @SerializedName("horoscope") val horoscope: String, // Texto de la predicción
    @SerializedName("sign") val sign: String // Signo al que pertenece la predicción
) {
    // Función que transforma esta respuesta en un objeto del dominio de la app
    fun toDomain(): PredictionModel {
        return PredictionModel(
            horoscope = horoscope,
            sign = sign
        )
    }
}