package com.javimutis.horoscapp.data.network.response

import com.javimutis.horoscapp.motherObject.HoroscopeMotherObject
import com.javimutis.horoscapp.motherObject.HoroscopeMotherObject.anyResponse
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import io.kotest.matchers.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PredictionResponseTest {
    @Test
    fun `toDomain should return a correct PredictionModel`() {

        //Given -> Datos de entrada
        val predictionResponse = anyResponse

        //When -> Acción que quiero probar
        val predictionModel = predictionResponse.toDomain()

        //Then -> Resultado esperado
        predictionModel.sign shouldBe predictionResponse.sign
        predictionModel.horoscope shouldBe predictionResponse.horoscope
    }
}