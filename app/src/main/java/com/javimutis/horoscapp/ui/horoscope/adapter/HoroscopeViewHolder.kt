package com.javimutis.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.javimutis.horoscapp.databinding.ItemHoroscopeBinding
import com.javimutis.horoscapp.domain.model.HoroscopeInfo

// Clase ViewHolder que representa cada ítem de la lista en el RecyclerView
class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Usamos ViewBinding para acceder a las vistas de manera segura
    private val binding = ItemHoroscopeBinding.bind(view)

    // Método que asigna los datos del horóscopo a las vistas del ViewHolder
    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        val context = binding.tvTitle.context // Obtener contexto para usar en las cadenas de texto

        binding.ivHoroscope.setImageResource(horoscopeInfo.img) // Asigna la imagen del horóscopo
        binding.tvTitle.text =
            context.getString(horoscopeInfo.name) // Asigna el nombre del horóscopo

        // Configura el comportamiento de clic en el item
        binding.parent.setOnClickListener {
            startRotationAnimation(binding.ivHoroscope) { // Animación de rotación al hacer clic
                onItemSelected(horoscopeInfo) // Llama al callback pasando el horóscopo seleccionado
            }
        }
    }

    // Método para aplicar una animación de rotación en la vista
    private fun startRotationAnimation(view: View, newLambda: () -> Unit) {
        view.animate().apply {
            duration = 500 // Duración de la animación
            interpolator = LinearInterpolator() // Interpolador para movimiento suave
            rotationBy(360f) // Rota la vista 360 grados
            withEndAction { newLambda() } // Ejecuta la acción después de la animación
            start() // Inicia la animación
        }
    }
}