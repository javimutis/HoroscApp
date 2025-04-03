package com.javimutis.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.javimutis.horoscapp.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel // Indica que este ViewModel puede recibir inyección de dependencias con Hilt
class HoroscopeViewModel @Inject constructor() : ViewModel() {
    // Flow permite la comunicación constante entre el ViewModel y la UI
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList()) // Estado interno mutable
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope // Estado inmutable para la UI

    // Init: Es como el onCreate de las actividades, se ejecuta al instanciar el ViewModel
    init {
        _horoscope.value = listOf() // Se inicializa la lista de horóscopos vacía
    }
}

