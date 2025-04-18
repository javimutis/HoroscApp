package com.javimutis.horoscapp.data.providers

import com.javimutis.horoscapp.domain.model.HoroscopeInfo
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Aquarius
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Aries
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Cancer
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Capricorn
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Gemini
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Leo
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Libra
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Pisces
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Sagittarius
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Scorpio
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Taurus
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Virgo
import javax.inject.Inject

// Esta clase se encarga de entregar la lista completa de signos del zodiaco
class HoroscopeProvider @Inject constructor() {

    // Esta función retorna una lista con todos los signos disponibles en la app
    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            Aries,
            Taurus,
            Gemini,
            Cancer,
            Leo,
            Virgo,
            Libra,
            Scorpio,
            Sagittarius,
            Capricorn,
            Aquarius,
            Pisces
        )
    }
}