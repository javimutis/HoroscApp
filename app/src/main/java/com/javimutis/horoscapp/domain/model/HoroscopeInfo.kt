package com.javimutis.horoscapp.domain.model

import com.javimutis.horoscapp.R

// Clase sellada (sealed class) que representa la información de cada signo del zodiaco.
// Cada signo es un objeto dentro de la clase y tiene dos propiedades:
// - img: hace referencia a la imagen del signo.
// - name: hace referencia al nombre del signo.
sealed class HoroscopeInfo(val img: Int, val name: Int) {

    object Aries : HoroscopeInfo(R.drawable.aries, R.string.aries)
    object Taurus : HoroscopeInfo(R.drawable.taurus, R.string.taurus)
    object Gemini : HoroscopeInfo(R.drawable.geminis, R.string.gemini)
    object Cancer : HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    object Leo : HoroscopeInfo(R.drawable.leo, R.string.leo)
    object Virgo : HoroscopeInfo(R.drawable.virgo, R.string.virgo)
    object Libra : HoroscopeInfo(R.drawable.libra, R.string.libra)
    object Scorpio : HoroscopeInfo(R.drawable.scorpio, R.string.scorpio)
    object Sagittarius : HoroscopeInfo(R.drawable.sagittarius, R.string.sagittarius)
    object Capricorn : HoroscopeInfo(R.drawable.capricorn, R.string.capricorn)
    object Aquarius : HoroscopeInfo(R.drawable.aquarius, R.string.aquarius)
    object Pisces : HoroscopeInfo(R.drawable.pisces, R.string.pisces)
}