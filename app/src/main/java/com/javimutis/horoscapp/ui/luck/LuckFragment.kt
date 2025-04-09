package com.javimutis.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.javimutis.horoscapp.R
import com.javimutis.horoscapp.databinding.FragmentLuckBinding
import com.javimutis.horoscapp.ui.core.listeners.OnSwipeTouchListener
import com.javimutis.horoscapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject

// Habilita la inyección de dependencias con Hilt en este Fragment
@AndroidEntryPoint
// Este Fragment muestra una carta de la suerte al hacer swipe sobre una ruleta animada.

class LuckFragment : Fragment() {

    // Variable privada que almacena el binding del layout
    private var _binding: FragmentLuckBinding? = null

    // Getter no nulo para acceder al binding más fácilmente
    private val binding get() = _binding!!

    // Inyección del proveedor de cartas aleatorias (RandomCardProvider)
    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    // Se ejecuta una vez que la vista ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI() // Inicializa la interfaz de usuario
    }

    // Función que prepara la interfaz: muestra predicción e inicia los listeners
    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    // Muestra una predicción aleatoria
    private fun preparePrediction() {
        val currentLuck = randomCardProvider.getLucky() // Obtiene una carta aleatoria
        currentLuck?.let { luck ->
            val currentPrediction = getString(luck.text) // Obtiene el texto asociado a la carta
            binding.tvLucky.text = currentPrediction // Muestra el texto
            binding.ivLuckyCard.setImageResource(luck.image) // Muestra la imagen
            // Comparte el resultado al presionar el botón
            binding.tvShare.setOnClickListener { shareResult(currentPrediction) }
        }
    }

    // Abre un intent para compartir la predicción como texto
    private fun shareResult(prediction: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    // Configura el listener de swipe en la ruleta
    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        // Detecta swipe hacia izquierda o derecha sobre la imagen de la ruleta
        binding.ivRoulette.setOnTouchListener(object : OnSwipeTouchListener(requireContext()) {
            override fun onSwipeRight() {
                spinRoulette() // Gira la ruleta si hace swipe hacia la derecha
            }

            override fun onSwipeLeft() {
                spinRoulette() // Gira también si es hacia la izquierda
            }
        })
    }

    // Hace girar la ruleta con animación
    private fun spinRoulette() {
        val random = Random()
        val degrees = random.nextInt(1440) + 360 // Ángulo aleatorio de giro
        val animator =
            ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000 // Dura 2 segundos
        animator.interpolator = DecelerateInterpolator() // Hace que el giro desacelere al final
        animator.doOnEnd { slideCard() } // Al terminar el giro, muestra la carta
        animator.start()
    }

    // Anima la aparición de la carta desde abajo
    private fun slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        // Listener para saber cuándo termina la animación
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                binding.ivReverse.isVisible = true // Muestra imagen intermedia
            }

            override fun onAnimationEnd(p0: Animation?) {
                growCard() // Una vez que termina, agranda la carta
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        binding.ivReverse.startAnimation(slideUpAnimation)
    }

    // Anima la carta agrandándose
    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

        growAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.ivReverse.isVisible = false // Oculta la imagen intermedia
                showPremonitionView() // Muestra el resultado final
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        binding.ivReverse.startAnimation(growAnimation)
    }

    // Aplica efecto fade para cambiar de vista previa a predicción final
    private fun showPremonitionView() {
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f) // Desvanece preview
        disappearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f) // Aparece predicción
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }

    // Infla la vista del fragmento usando ViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}