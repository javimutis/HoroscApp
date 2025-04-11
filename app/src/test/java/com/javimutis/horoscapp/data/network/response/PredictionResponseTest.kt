package com.javimutis.horoscapp.data.network.response

import io.kotest.matchers.shouldBe
import org.junit.Test


class PredictionResponseTest {
    @Test
    fun `toDomain should return a correct PredictionModel`() {

        //Given -> Datos de entrada
        val predictionResponse = PredictionResponse(
            "date", "prediction", "taurus"
        )
        //When -> Acción que quiero probar
        val predictionModel = predictionResponse.toDomain()
        //Then -> Resultado esperado
        predictionModel.sign shouldBe predictionResponse.sign
        predictionModel.horoscope shouldBe predictionResponse.horoscope
    }
}