package com.javimutis.horoscapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// Modelo que representa una carta de la suerte
// image: ID del recurso drawable
// text: ID del string con el mensaje de la suerte
data class LuckyModel(
    @DrawableRes val image: Int,
    @StringRes val text: Int
)