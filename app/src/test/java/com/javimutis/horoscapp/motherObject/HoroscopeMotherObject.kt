// Este archivo contiene datos de prueba que podemos usar en distintos tests (por eso es "MotherObject")

package com.javimutis.horoscapp.motherObject

// Importa el modelo de respuesta desde la red
import com.javimutis.horoscapp.data.network.response.PredictionResponse

// Importa un modelo de dominio con los signos zodiacales
import com.javimutis.horoscapp.domain.model.HoroscopeInfo

// Objeto que contiene datos de prueba reutilizables
object HoroscopeMotherObject {

    // Objeto falso que simula una respuesta de la API
    val anyResponse = PredictionResponse("date", "prediction", "taurus")

    // Lista de todos los signos zodiacales (modelo del dominio)
    val horoscopeInfoList = listOf(
        HoroscopeInfo.Aries,
        HoroscopeInfo.Taurus,
        HoroscopeInfo.Gemini,
        HoroscopeInfo.Cancer,
        HoroscopeInfo.Leo,
        HoroscopeInfo.Virgo,
        HoroscopeInfo.Libra,
        HoroscopeInfo.Scorpio,
        HoroscopeInfo.Sagittarius,
        HoroscopeInfo.Capricorn,
        HoroscopeInfo.Aquarius,
        HoroscopeInfo.Pisces
    )
}
