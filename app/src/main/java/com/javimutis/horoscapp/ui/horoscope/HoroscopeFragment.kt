package com.javimutis.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.javimutis.horoscapp.databinding.FragmentHoroscopeBinding
import com.javimutis.horoscapp.domain.model.HoroscopeInfo
import com.javimutis.horoscapp.domain.model.HoroscopeModel
import com.javimutis.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint // Hilt lo marca como un fragmento inyectable
class HoroscopeFragment : Fragment() {
    // Conexión entre el ViewModel y el Fragment
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()
    private lateinit var horoscopeAdapter: HoroscopeAdapter

    // Variable que representa el binding de la vista
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    // Método que se ejecuta cuando la vista ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI() // Inicializa la UI del fragmento
    }

    // Método que inicializa la UI
    private fun initUI() {
        initList() // Inicializa la lista de horóscopos
        initUIState() // Inicializa el estado de la UI, escuchando los cambios del ViewModel
    }

    private fun initList() {
        // Inicializa el adaptador para el RecyclerView con una acción cuando un item es seleccionado
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
           val type = when(it){
                HoroscopeInfo.Aquarius -> HoroscopeModel.Aquarius
                HoroscopeInfo.Aries -> HoroscopeModel.Aries
                HoroscopeInfo.Cancer -> HoroscopeModel.Cancer
                HoroscopeInfo.Capricorn -> HoroscopeModel.Capricorn
                HoroscopeInfo.Gemini -> HoroscopeModel.Gemini
                HoroscopeInfo.Leo -> HoroscopeModel.Leo
                HoroscopeInfo.Libra -> HoroscopeModel.Libra
                HoroscopeInfo.Pisces -> HoroscopeModel.Pisces
                HoroscopeInfo.Sagittarius -> HoroscopeModel.Sagittarius
                HoroscopeInfo.Scorpio -> HoroscopeModel.Scorpio
                HoroscopeInfo.Taurus -> HoroscopeModel.Taurus
                HoroscopeInfo.Virgo -> HoroscopeModel.Virgo
            }

            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        })

        // Configura el RecyclerView con un GridLayoutManager y asigna el adaptador
        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2) // Dos columnas
            adapter = horoscopeAdapter // Asigna el adaptador a RecyclerView
        }
    }

    // Método que observa cambios en el ViewModel y actualiza la UI
    private fun initUIState() {
        // Usa una corrutina para observar cambios en los datos del ViewModel
        lifecycleScope.launch {
            // Solo observa cuando el ciclo de vida está en estado STARTED o superior
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {
                    // Actualiza la lista de horóscopos en el adaptador cuando cambian los datos
                    horoscopeAdapter.updateList(it)
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
        return binding.root // Devuelve la raíz de la vista inflada
    }
}