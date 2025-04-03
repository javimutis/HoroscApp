package com.javimutis.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.javimutis.horoscapp.databinding.FragmentHoroscopeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint // Hilt lo marca como un fragmento inyectable
class HoroscopeFragment : Fragment() {
    // Conexión entre el ViewModel y el Fragment
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    // Variable que representa el binding de la vista
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    // Método que se ejecuta cuando la vista ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    // Método que inicializa la UI
    private fun initUI() {
        initUIState()
    }

    // Método que inicializa el estado de la UI
    private fun initUIState() {
        // Se usa una corrutina para observar cambios en los datos del ViewModel
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {
                    Log.i("MutisApp", it.toString()) // Se imprime la lista de horóscopos en el log
                }
            }
        }
    }

    // Método que configura la vista del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}