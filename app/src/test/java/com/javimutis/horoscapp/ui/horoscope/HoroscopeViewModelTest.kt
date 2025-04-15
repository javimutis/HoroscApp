// Este archivo prueba que el ViewModel de horóscopos carga los datos correctamente al ser creado

package com.javimutis.horoscapp.ui.horoscope

// Importamos el proveedor que entrega los signos del horóscopo
import com.javimutis.horoscapp.data.providers.HoroscopeProvider

// Importamos la lista de signos de prueba
import com.javimutis.horoscapp.motherObject.HoroscopeMotherObject.horoscopeInfoList

// Herramientas de MockK para simular objetos y comportamientos
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.MockK

// Importa función para verificar condiciones en tests
import org.junit.Assert.assertTrue

// Anotaciones y funciones para tests
import org.junit.Before
import org.junit.Test

// Clase que contiene las pruebas para el ViewModel del horóscopo
class HoroscopeViewModelTest{

    // Esta anotación indica que este objeto será simulado con MockK
    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    // Variable para el ViewModel que queremos probar
    private lateinit var viewModel: HoroscopeViewModel

    // Esta función se ejecuta antes de cada test para inicializar MockK
    @Before
    fun setUp(){
        // Se inicializan las anotaciones de MockK
        init(this, relaxUnitFun = true)
    }

    // Este test comprueba que al crear el ViewModel, se cargan los signos del horóscopo
    @Test
    fun `when viewmodel is created then horoscopes are loaded`(){
        // Simula que el proveedor devuelve una lista de signos
        every { horoscopeProvider.getHoroscopes() } returns horoscopeInfoList

        // Se crea el ViewModel usando el proveedor simulado
        viewModel = HoroscopeViewModel(horoscopeProvider)

        // Se accede a la lista de horóscopos dentro del ViewModel
        val horoscopes = viewModel.horoscope.value

        // Se verifica que la lista no esté vacía
        assertTrue(horoscopes.isNotEmpty())
    }
}
