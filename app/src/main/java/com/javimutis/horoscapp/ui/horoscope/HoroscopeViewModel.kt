package com.javimutis.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.javimutis.horoscapp.data.providers.HoroscopeProvider
import com.javimutis.horoscapp.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel // Indica que este ViewModel puede recibir inyección de dependencias con Hilt
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider) :
    ViewModel() {
    // Flow permite la comunicación constante entre el ViewModel y la UI
    private var _horoscope =
        MutableStateFlow<List<HoroscopeInfo>>(emptyList()) // Estado interno mutable de los horóscopos
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope // Estado inmutable para la UI

    // Init: Se ejecuta cuando el ViewModel es creado, es el equivalente al onCreate de Activity
    init {
        // Llama al proveedor para obtener los horóscopos y asignarlos al estado
        _horoscope.value = horoscopeProvider.getHoroscopes()
    }
}