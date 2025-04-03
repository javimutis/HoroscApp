package com.javimutis.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.javimutis.horoscapp.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor() : ViewModel() {
    //Flow comunicación constante entre dos vistas, siempre que hay un dato se le va a notificar a la otra vista
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    //init: Es como el OnCreate de las activities
    init {
        _horoscope.value = listOf()

    }


}
