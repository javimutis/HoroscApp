package com.javimutis.horoscapp.ui.horoscope.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.javimutis.horoscapp.databinding.ItemHoroscopeBinding
import com.javimutis.horoscapp.domain.model.HoroscopeInfo

// ViewHolder es una clase que representa cada elemento de la lista en el RecyclerView
class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder(view) {
    // Se usa ViewBinding para acceder a los elementos de la vista de manera segura
    private val binding = ItemHoroscopeBinding.bind(view)

    // Método que se encarga de asignar la información del horóscopo a la vista
    fun render(horoscopeInfo: HoroscopeInfo){
        val context = binding.tvTitle.context // Se obtiene el contexto de la vista
        binding.ivHoroscope.setImageResource(horoscopeInfo.img) // Se asigna la imagen del horóscopo
        binding.tvTitle.text = context.getString(horoscopeInfo.name) // Se asigna el nombre del horóscopo
    }
}