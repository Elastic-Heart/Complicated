package com.example.complicated.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.complicated.R
import com.example.complicated.data.CondoUnit

class CondoAdapter : RecyclerView.Adapter<CondoAdapter.CondoViewHolder>() {

    var condos = listOf<CondoUnit>()

    inner class CondoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.condoName)
        private val description = view.findViewById<TextView>(R.id.condoDescription)
        private val image = view.findViewById<ImageView>(R.id.condoImage)

        operator fun invoke(condoUnit: CondoUnit) {
            name.text = condoUnit.name
            description.text = condoUnit.description
            image.load(condoUnit.imageURL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CondoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.unit_card_view, parent, false)
        return CondoViewHolder(view)
    }

    override fun getItemCount(): Int = condos.size

    override fun onBindViewHolder(holder: CondoViewHolder, position: Int) {
        val condo = condos[position]
        holder(condo)
    }
}