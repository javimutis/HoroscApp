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
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Aquarius
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Aries
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Cancer
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Capricorn
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Gemini
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Leo
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Libra
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Pisces
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Sagittarius
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Scorpio
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Taurus
import com.javimutis.horoscapp.domain.model.HoroscopeInfo.Virgo
import com.javimutis.horoscapp.domain.model.HoroscopeModel
import com.javimutis.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint // Marca este fragmento como inyectable por Hilt
class HoroscopeFragment : Fragment() {
    // ViewModel asociado a este fragmento
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()
    private lateinit var horoscopeAdapter: HoroscopeAdapter

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!


    // Este método se ejecuta cuando la vista ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI() // Inicializa la interfaz de usuario
    }

    // Inicializa la interfaz de usuario
    private fun initUI() {
        initList() // Inicializa el RecyclerView con la lista de horóscopos
        initUIState() // Observa el estado de la UI y actualiza con datos del ViewModel
    }

    private fun initList() {
        // Inicializa el adaptador para el RecyclerView con una acción cuando un item es seleccionado
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            val type = when (it) {
                Aquarius -> HoroscopeModel.Aquarius
                Aries -> HoroscopeModel.Aries
                Cancer -> HoroscopeModel.Cancer
                Capricorn -> HoroscopeModel.Capricorn
                Gemini -> HoroscopeModel.Gemini
                Leo -> HoroscopeModel.Leo
                Libra -> HoroscopeModel.Libra
                Pisces -> HoroscopeModel.Pisces
                Sagittarius -> HoroscopeModel.Sagittarius
                Scorpio -> HoroscopeModel.Scorpio
                Taurus -> HoroscopeModel.Taurus
                Virgo -> HoroscopeModel.Virgo
            }
            // Navega a un nuevo fragmento con el tipo de horóscopo seleccionado
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