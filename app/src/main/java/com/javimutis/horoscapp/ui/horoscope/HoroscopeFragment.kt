package com.javimutis.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.javimutis.horoscapp.databinding.FragmentHoroscopeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {
    //conexión entre viewmodel y fragment
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    // este es el metodo que configura la vista del fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}