// Este archivo prueba que el proveedor de cartas de la suerte esté devolviendo una carta aleatoria

package com.javimutis.horoscapp.ui.providers

// Importa funciones de prueba de JUnit
import org.junit.Assert.*
import org.junit.Test

// Clase que contiene pruebas para el proveedor de cartas de la suerte
class RandomCardProviderTest {

    // Este test comprueba que el método getLucky() devuelve algo (no es nulo)
    @Test
    fun `getRandomCard should return a random card`(){
        // Se crea una instancia del proveedor
        val randomCard = RandomCardProvider()

        // Se obtiene una carta aleatoria
        val card = randomCard.getLucky()

        // Se verifica que la carta no sea nula
        assertNotNull(card)
    }
}
