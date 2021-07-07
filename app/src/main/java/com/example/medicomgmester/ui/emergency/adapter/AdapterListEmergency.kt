package com.example.medicomgmester.ui.emergency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.R
import com.example.medicomgmester.model.Emergency
import com.example.medicomgmester.ui.emergency.holder.EmergencyHolder

class AdapterListEmergency(private var coWork: List<Emergency>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(items: List<Emergency>) {
        coWork = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        EmergencyHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = coWork.size

    override fun getItemViewType(position: Int): Int = R.layout.item_theme_emergency

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as EmergencyHolder).onBind(coWork[position])


}