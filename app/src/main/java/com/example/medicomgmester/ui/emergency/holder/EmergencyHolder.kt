package com.example.medicomgmester.ui.emergency.holder

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.extension.load
import com.example.medicomgmester.model.Emergency
import kotlinx.android.synthetic.main.item_theme_emergency.view.*


class EmergencyHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(data_emergency: Emergency) {
        itemView.apply {
            name_emergency.text = data_emergency.name_department
            call_emergency.text = data_emergency.contact_number
            email_emergency.text = data_emergency.line
            image_view_emergency.load(data_emergency.image)
            layout_emergency_bg.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${data_emergency.contact_number}")
                context.startActivity(intent)
            }

          }
        }
    }

