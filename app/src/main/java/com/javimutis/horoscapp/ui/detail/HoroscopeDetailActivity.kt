package com.javimutis.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.javimutis.horoscapp.R
import com.javimutis.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.javimutis.horoscapp.domain.model.HoroscopeModel
import com.javimutis.horoscapp.domain.model.HoroscopeModel.*
import com.javimutis.horoscapp.ui.detail.HoroscopeDetailState.Error
import com.javimutis.horoscapp.ui.detail.HoroscopeDetailState.Loading
import com.javimutis.horoscapp.ui.detail.HoroscopeDetailState.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        args.type

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)
    }

    private fun initUI() {
        initListener()
        initUIState()
    }

    private fun initListener() {
        binding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        is Error -> errorState()
                        Loading -> loadingState()
                        is Success -> succesState(it)
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }


    private fun succesState(state: HoroscopeDetailState.Success) {
        binding.pb.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction

        val image = when(state.horoscopeModel){
            Aquarius -> R.drawable.detail_aquarius
            Aries -> R.drawable.detail_aries
            Cancer -> R.drawable.detail_cancer
            Capricorn -> R.drawable.detail_capricorn
            Gemini -> R.drawable.detail_gemini
            Leo -> R.drawable.detail_leo
            Libra -> R.drawable.detail_libra
            Pisces -> R.drawable.detail_pisces
            Sagittarius -> R.drawable.detail_sagittarius
            Scorpio -> R.drawable.detail_scorpio
            Taurus -> R.drawable.detail_taurus
            Virgo -> R.drawable.detail_virgo
        }
        binding.ivDetail.setImageResource(image)
    }
}