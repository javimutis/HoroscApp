package com.javimutis.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javimutis.horoscapp.domain.model.HoroscopeModel
import com.javimutis.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// Indica que este ViewModel usará la anotación de Hilt para la inyección de dependencias
@HiltViewModel
// ViewModel que maneja la lógica y datos de la pantalla de detalle del horóscopo
class HoroscopeDetailViewModel @Inject constructor(
    // El ViewModel recibe un caso de uso como dependencia (inyectado por Hilt)
    private val getPredictionUseCase: GetPredictionUseCase
) : ViewModel() {

    // Variable interna (privada) que representa el estado actual de la UI usando StateFlow
    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)

    // Variable pública e inmutable que expone el estado para ser observado desde la UI
    val state: StateFlow<HoroscopeDetailState> = _state

    // Variable para guardar el signo del horóscopo que se está visualizando
    lateinit var horoscope: HoroscopeModel

    // Función que se llama desde la UI para obtener la predicción de un signo específico
    fun getHoroscope(sign: HoroscopeModel) {
        // Guarda el signo actual en la variable local
        horoscope = sign

        // Lanza una corrutina en el scope del ViewModel (se cancela automáticamente si el ViewModel se destruye)
        viewModelScope.launch {

            // Cambia el estado a "Loading" para indicar que se está cargando la información
            _state.value = HoroscopeDetailState.Loading

            // Con 'withContext(Dispatchers.IO)' cambiamos a un hilo de entrada/salida (ideal para llamadas de red o base de datos)
            val result = withContext(Dispatchers.IO) {
                // Llama al caso de uso que obtiene la predicción del signo (nombre como string)
                getPredictionUseCase(sign.name)
            }

            // Si el resultado no es nulo, cambia el estado a "Success"
            if (result != null) {
                _state.value = HoroscopeDetailState.Success(
                    prediction = result.horoscope, // Texto con la predicción del día
                    sign = result.sign,            // Nombre del signo (como texto)
                    horoscopeModel = horoscope     // El modelo del signo actual
                )
            } else {
                // Si hubo un error, cambia el estado a "Error" con un mensaje
                _state.value = HoroscopeDetailState.Error("Ha ocurrido un error, inténtelo más tarde")
            }
        }
    }
}