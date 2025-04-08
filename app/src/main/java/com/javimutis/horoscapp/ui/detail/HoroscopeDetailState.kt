package com.javimutis.horoscapp.ui.detail

import com.javimutis.horoscapp.domain.model.HoroscopeModel

// Clase sellada que representa los diferentes estados posibles de la UI
sealed class HoroscopeDetailState {
    // Estado cuando se está cargando la información
    object Loading : HoroscopeDetailState()

    // Estado cuando la información se ha cargado correctamente
    data class Success(
        val prediction: String, // Predicción del horóscopo para el día
        val sign: String,       // Nombre del signo
        val horoscopeModel: HoroscopeModel // El modelo del signo (usado para pasar información a la UI)
    ) : HoroscopeDetailState()

    // Estado cuando ocurrió un error al obtener la información
    data class Error(val message: String) : HoroscopeDetailState()
}