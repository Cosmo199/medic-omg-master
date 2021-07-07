package com.example.medicomgmester.ui.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.R
import com.example.medicomgmester.model.DataDoc
import com.example.medicomgmester.ui.gallery.holder.GalleryHolder

class AdapterListGallery(private var coWork: List<DataDoc>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(items: List<DataDoc>) {
        coWork = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        GalleryHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = coWork.size

    override fun getItemViewType(position: Int): Int = R.layout.item_theme_data

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as GalleryHolder).onBind(coWork[position])


}