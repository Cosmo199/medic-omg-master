package com.example.medicomgmester.ui.medic.holder

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.extension.load
import com.example.medicomgmester.model.DataMedic
import kotlinx.android.synthetic.main.item_theme_medic.view.*

class MedicHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(data_medic: DataMedic) {
        itemView.apply {
            name_medic.text = data_medic.name
            image_view_medic.load(data_medic.image)
            email_medic.text = data_medic.contact_email
            layout_medic.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:"+data_medic.contact_email)
                intent.putExtra(Intent.EXTRA_EMAIL, "test")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hello World")
                context.startActivity(intent)
            }
        }
    }




}