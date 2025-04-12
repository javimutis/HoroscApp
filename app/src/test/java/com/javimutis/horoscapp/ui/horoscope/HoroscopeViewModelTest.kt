package com.javimutis.horoscapp.ui.horoscope

import com.javimutis.horoscapp.data.providers.HoroscopeProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class HoroscopeViewModelTest {

    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var viewModel: HoroscopeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `when viewmodel is created then getHoroscope are loaded`() {
        every{ horoscopeProvider.getHoroscopes()} returns listOf()
        viewModel = HoroscopeViewModel(horoscopeProvider)
    }

}