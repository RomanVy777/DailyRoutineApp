package com.example.dailyroutineapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyroutineapp.R
import com.example.dailyroutineapp.model.Rutina

class RutinaAdapter(private val listaRutinas: List<Rutina>) :
    RecyclerView.Adapter<RutinaAdapter.RutinaViewHolder>() {

    class RutinaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombre) // Asegúrate de tener estos IDs en item_rutina.xml
        val tvHora: TextView = view.findViewById(R.id.tvHora)
        val tvEstado: TextView = view.findViewById(R.id.tvEstado)
        val ivIcono: ImageView = view.findViewById(R.id.ivIcono)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutinaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rutina, parent, false)
        return RutinaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RutinaViewHolder, position: Int) {
        val rutina = listaRutinas[position]
        holder.tvNombre.text = rutina.nombre
        holder.tvHora.text = rutina.hora
        holder.tvEstado.text = rutina.estado
        holder.ivIcono.setImageResource(rutina.tipoIcono)

        // Cambiar el fondo según el estado
        if (rutina.estado == "Pendiente") {
            holder.tvEstado.setBackgroundResource(R.drawable.bg_tag_pendiente)
        } else {
            holder.tvEstado.setBackgroundResource(R.drawable.bg_tag_completada)
        }
    }

    override fun getItemCount() = listaRutinas.size
}