package com.javimutis.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javimutis.horoscapp.R
import com.javimutis.horoscapp.domain.model.HoroscopeInfo

// Adaptador para la lista de signos del zodiaco en un RecyclerView
class HoroscopeAdapter(private var horoscopeList: List<HoroscopeInfo> = emptyList()) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    // Método que se ejecuta cuando se necesita crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        return HoroscopeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        )
    }

    // Devuelve la cantidad de elementos en la lista
    override fun getItemCount() = horoscopeList.size

    // Método que le indica al ViewHolder qué elemento debe mostrar en cada posición
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscopeList[position])
    }
}
