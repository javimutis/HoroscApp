package com.javimutis.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.javimutis.horoscapp.databinding.ItemHoroscopeBinding
import com.javimutis.horoscapp.domain.model.HoroscopeInfo

// ViewHolder es una clase que representa cada elemento de la lista en el RecyclerView
class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Se usa ViewBinding para acceder a los elementos de la vista de manera segura
    private val binding = ItemHoroscopeBinding.bind(view)

    // Método que se encarga de asignar la información del horóscopo a la vista
    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        // Se obtiene el contexto de la vista para usarlo más adelante en la asignación de texto
        val context = binding.tvTitle.context

        // Se asigna la imagen del horóscopo
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)

        // Se asigna el nombre del horóscopo utilizando el contexto para obtener la cadena de texto
        binding.tvTitle.text = context.getString(horoscopeInfo.name)

        // Se define el comportamiento cuando se hace clic en el item
        binding.parent.setOnClickListener {
            // Se inicia una animación de rotación en la imagen antes de ejecutar la acción
            startRotationAnimation(binding.ivHoroscope) {
                // Llama a la función onItemSelected, pasando el horóscopo como parámetro
                onItemSelected(horoscopeInfo)
            }
        }
    }

    // Método que aplica una animación de rotación en la vista
    private fun startRotationAnimation(view: View, newLambda: () -> Unit) {
        view.animate().apply {
            // Duración de la animación (500ms)
            duration = 500
            // Interpolador lineal para un movimiento suave
            interpolator = LinearInterpolator()
            // Se rota la vista 360 grados
            rotationBy(360f)
            // Al finalizar la animación, se ejecuta la acción nueva (onItemSelected)
            withEndAction { newLambda() }
            start() // Comienza la animación
        }
    }
}