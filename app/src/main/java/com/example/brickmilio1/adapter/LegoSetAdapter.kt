package com.example.brickmilio1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.brickmilio1.R
import com.example.brickmilio1.model.LegoSet
import com.example.brickmilio1.viewmodel.LegoSetViewModel

class LegoSetAdapter(private val viewModel: LegoSetViewModel) :
    ListAdapter<LegoSet, LegoSetAdapter.LegoSetViewHolder>(DiffCallback()) {

    class LegoSetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvCategoria: TextView = itemView.findViewById(R.id.tvCategoria)
        val tvPiezas: TextView = itemView.findViewById(R.id.tvPiezas)
        val tvTiempo: TextView = itemView.findViewById(R.id.tvTiempo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LegoSetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lego_set, parent, false)
        return LegoSetViewHolder(view)
    }

    override fun onBindViewHolder(holder: LegoSetViewHolder, position: Int) {
        val legoSet = getItem(position)
        holder.tvNombre.text = legoSet.nombre
        holder.tvCategoria.text = legoSet.categoria
        holder.tvPiezas.text = "${legoSet.numeroPiezas} piezas"

        if (legoSet.estado != "armado") {
            val tiempo = viewModel.formatearTiempo(legoSet.tiempoEstimadoMin)
            holder.tvTiempo.text = "⏱ $tiempo"
            holder.tvTiempo.visibility = View.VISIBLE
        } else {
            holder.tvTiempo.visibility = View.GONE
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<LegoSet>() {
        override fun areItemsTheSame(oldItem: LegoSet, newItem: LegoSet) =
            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: LegoSet, newItem: LegoSet) =
            oldItem == newItem
    }
}