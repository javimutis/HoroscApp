package com.javimutis.horoscapp.domain.model

// Data class que representa una predicción del horóscopo.
// Esta clase guarda el mensaje del horóscopo y el signo al que pertenece.
// - horoscope: el texto con la predicción.
// - sign: el nombre del signo.
data class PredictionModel(
    val horoscope: String,
    val sign: String
)