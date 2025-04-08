package com.javimutis.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.javimutis.horoscapp.data.providers.HoroscopeProvider
import com.javimutis.horoscapp.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel // Hilt indica que este ViewModel puede recibir dependencias automáticamente
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider) :
    ViewModel() {
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList()) // Estado mutable de los horóscopos
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope // Estado inmutable para la UI

    // Al iniciar el ViewModel, se obtienen los horóscopos del proveedor
    init {
        _horoscope.value = horoscopeProvider.getHoroscopes() // Obtiene los horóscopos
    }
}