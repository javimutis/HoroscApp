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
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Aquarius
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Aries
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Cancer
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Capricorn
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Gemini
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Leo
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Libra
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Pisces
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Sagittarius
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Scorpio
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Taurus
import com.javimutis.horoscapp.domain.model.HoroscopeModel.Virgo
import com.javimutis.horoscapp.ui.detail.HoroscopeDetailState.Error
import com.javimutis.horoscapp.ui.detail.HoroscopeDetailState.Loading
import com.javimutis.horoscapp.ui.detail.HoroscopeDetailState.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// Hilt habilita la inyección de dependencias en esta Activity
@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    // ViewBinding para acceder de forma segura y sencilla a las vistas del layout
    private lateinit var binding: ActivityHoroscopeDetailBinding

    // ViewModel de esta pantalla, lo obtenemos con delegación usando viewModels()
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    // Obtenemos los argumentos enviados desde el fragmento anterior (el signo seleccionado)
    private val args: HoroscopeDetailActivityArgs by navArgs()

    // Método que se llama cuando se crea la Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge() // Activa el modo sin bordes (pantalla completa moderna)

        // Inflamos la vista con ViewBinding
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root) // Mostramos la vista en pantalla

        args.type // Aquí estamos accediendo al argumento (signo), aunque no lo usamos directamente aquí

        // Ajustamos automáticamente el padding según la barra de estado y navegación
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos la interfaz de usuario
        initUI()

        // Pedimos la predicción para el signo que recibimos como argumento
        horoscopeDetailViewModel.getHoroscope(args.type)
    }

    // Método para inicializar todo lo relacionado con la UI
    private fun initUI() {
        initListener() // Configura los listeners (botones, etc.)
        initUIState()  // Observa los cambios en el estado del ViewModel para actualizar la vista
    }

    // Configura el botón de "volver"
    private fun initListener() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Vuelve a la pantalla anterior
        }
    }

    // Observa el estado del ViewModel y actualiza la vista según el caso
    private fun initUIState() {
        lifecycleScope.launch {
            // Solo observará los cambios cuando el ciclo de vida esté al menos en STARTED
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Observa el flujo de estado del ViewModel
                horoscopeDetailViewModel.state.collect {
                    // Dependiendo del tipo de estado, se ejecuta una acción diferente
                    when (it) {
                        is Error -> errorState()       // Muestra el estado de error
                        Loading -> loadingState()      // Muestra el progreso de carga
                        is Success -> succesState(it)  // Muestra los datos recibidos
                    }
                }
            }
        }
    }

    // Muestra el ProgressBar cuando está cargando
    private fun loadingState() {
        binding.pb.isVisible = true
    }

    // Oculta el ProgressBar cuando ocurre un error
    private fun errorState() {
        binding.pb.isVisible = false
        // Aquí podrías mostrar un mensaje de error con Toast o Snackbar
    }

    // Muestra el contenido cuando se reciben los datos exitosamente
    private fun succesState(state: HoroscopeDetailState.Success) {
        binding.pb.isVisible = false // Ocultamos el ProgressBar

        // Mostramos el signo y la predicción en los TextViews
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction

        // Elegimos la imagen correcta según el signo recibido
        val image = when (state.horoscopeModel) {
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

        // Asignamos la imagen al ImageView
        binding.ivDetail.setImageResource(image)
    }
}