package com.javimutis.horoscapp.domain


import com.javimutis.horoscapp.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign:String): PredictionModel?
}