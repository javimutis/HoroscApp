// Este archivo contiene pruebas para verificar que el mapeo entre el modelo de red y el modelo de dominio funcione correctamente

package com.javimutis.horoscapp.data.network.response

// Importa una clase que contiene objetos de prueba (falsos) para facilitar los tests
import com.javimutis.horoscapp.motherObject.HoroscopeMotherObject.anyResponse

// Importa funciones especiales para hacer comparaciones en pruebas (Kotest)
import io.kotest.matchers.shouldBe

// Importa las anotaciones y herramientas para escribir pruebas unitarias
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

// Indica que esta clase se va a ejecutar con JUnit4 (framework de testing)
@RunWith(JUnit4::class)
class PredictionResponseTest {

    // Esta es una función de prueba. Comprueba si el método `toDomain()` convierte correctamente los datos
    @Test
    fun `toDomain should return a correct PredictionModel`() {

        // Given -> Se define el dato de entrada: un objeto de tipo PredictionResponse "falso"
        val predictionResponse = anyResponse

        // When -> Se ejecuta la acción que queremos probar: convertir ese objeto en un modelo de dominio
        val predictionModel = predictionResponse.toDomain()

        // Then -> Se verifica que los campos del objeto convertido sean iguales al original
        predictionModel.sign shouldBe predictionResponse.sign
        predictionModel.horoscope shouldBe predictionResponse.horoscope
    }
}
