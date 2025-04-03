package com.javimutis.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javimutis.horoscapp.R
import com.javimutis.horoscapp.domain.model.HoroscopeInfo

// Adaptador para la lista de signos del zodiaco en un RecyclerView
class HoroscopeAdapter(
    private var horoscopeList: List<HoroscopeInfo> = emptyList(),  // Lista de signos zodiacales
    private val onItemSelected: (HoroscopeInfo) -> Unit  // Función de callback cuando se selecciona un ítem
) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    // Método para actualizar la lista de signos zodiacales en el adaptador
    fun updateList(list: List<HoroscopeInfo>) {
        horoscopeList = list // Actualiza la lista interna de signos zodiacales
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado
    }

    // Método que se ejecuta cuando se necesita crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        // Se infla el layout 'item_horoscope' para cada elemento de la lista
        return HoroscopeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        )
    }

    // Devuelve la cantidad de elementos en la lista
    override fun getItemCount() = horoscopeList.size

    // Método que le indica al ViewHolder qué elemento debe mostrar en cada posición
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        // Llama a la función render del ViewHolder para mostrar los datos del elemento en esa posición
        holder.render(horoscopeList[position], onItemSelected)
    }
}