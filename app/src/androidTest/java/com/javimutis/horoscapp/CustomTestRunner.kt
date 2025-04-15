// Paquete donde está esta clase
package com.javimutis.horoscapp

// Importamos lo necesario para cambiar la aplicación usada en tests
import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

// Importamos la clase especial de Hilt para pruebas
import dagger.hilt.android.testing.HiltTestApplication

// Esta clase reemplaza la clase Application normal por una especial para tests con Hilt
class CustomTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,       // Cargador de clases
        className: String?,     // Nombre de la clase Application (normalmente se ignora aquí)
        context: Context?       // Contexto de la app
    ): Application {
        // Retornamos una instancia de HiltTestApplication en vez de la aplicación real
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
