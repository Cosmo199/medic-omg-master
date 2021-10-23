package com.example.medicomgmester.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.R
import com.example.medicomgmester.model.Appointment
import com.example.medicomgmester.ui.home.holder.HomeHolder

class AdapterListHome(private var coWork: List<Appointment>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(items: List<Appointment>) {
        coWork = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        HomeHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = coWork.size

    override fun getItemViewType(position: Int): Int = R.layout.item_theme_appointment_card

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as HomeHolder).onBind(coWork[position])

}