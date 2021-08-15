package com.example.medicomgmester.ui.home.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.model.Appointment
import kotlinx.android.synthetic.main.item_theme_appointment_card.view.*

class HomeHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(data_appointment: Appointment) {
        itemView.apply {
            name_appointment.text = data_appointment.name_appointment
            date.text = data_appointment.date +" | "+ data_appointment.time
            name_doctor.text = data_appointment.name_doctor
            hn_number.text = "เลขประจำตัวผู้ป่วย :"+" "+data_appointment.hn_number
            hospital.text = data_appointment.hospital
            department.text = data_appointment.department
            email.text = data_appointment.email
            contact_number.text = data_appointment.contact_number
        }
    }

}