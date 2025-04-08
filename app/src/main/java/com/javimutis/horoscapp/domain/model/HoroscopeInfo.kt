package com.javimutis.horoscapp.domain.model

import com.javimutis.horoscapp.R

// Esta clase "sealed" representa los distintos signos del zodiaco.
// Una sealed class (clase sellada) permite definir un conjunto cerrado de subclases.
// Cada signo es un "data object", que es como una instancia única con sus datos.
// img: id de la imagen del signo (guardada en res/drawable)
// name: id del nombre del signo (guardado en res/strings.xml)
sealed class HoroscopeInfo(val img: Int, val name: Int) {

    // Cada uno de estos objetos representa un signo zodiacal con su imagen y nombre.
    data object Aries : HoroscopeInfo(R.drawable.aries, R.string.aries)
    data object Taurus : HoroscopeInfo(R.drawable.taurus, R.string.taurus)
    data object Gemini : HoroscopeInfo(R.drawable.geminis, R.string.gemini)
    data object Cancer : HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    data object Leo : HoroscopeInfo(R.drawable.leo, R.string.leo)
    data object Virgo : HoroscopeInfo(R.drawable.virgo, R.string.virgo)
    data object Libra : HoroscopeInfo(R.drawable.libra, R.string.libra)
    data object Scorpio : HoroscopeInfo(R.drawable.scorpio, R.string.scorpio)
    data object Sagittarius : HoroscopeInfo(R.drawable.sagittarius, R.string.sagittarius)
    data object Capricorn : HoroscopeInfo(R.drawable.capricorn, R.string.capricorn)
    data object Aquarius : HoroscopeInfo(R.drawable.aquarius, R.string.aquarius)
    data object Pisces : HoroscopeInfo(R.drawable.pisces, R.string.pisces)
}