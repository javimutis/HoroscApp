package com.javimutis.horoscapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HoroscApp : Application() // Hilt configura la inyección de dependencias desde la aplicación