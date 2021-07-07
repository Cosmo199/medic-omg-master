package com.example.medicomgmester.ui.medic.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.R
import com.example.medicomgmester.model.DataMedic
import com.example.medicomgmester.ui.medic.holder.MedicHolder

class AdapterListMedic(private var medicList: List<DataMedic>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(items: List<DataMedic>) {
        medicList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            MedicHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = medicList.size

    override fun getItemViewType(position: Int): Int = R.layout.item_theme_medic

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as MedicHolder).onBind(medicList[position])



}