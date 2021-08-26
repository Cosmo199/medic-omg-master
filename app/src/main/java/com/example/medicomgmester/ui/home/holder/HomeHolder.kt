package com.example.medicomgmester.ui.home.holder

import android.content.ContentValues.TAG
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.R
import com.example.medicomgmester.model.Appointment
import kotlinx.android.synthetic.main.item_theme_appointment_card.view.*
import java.text.SimpleDateFormat
import java.util.*

class HomeHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(data_appointment: Appointment) {
        itemView.apply {
            name_appointment.text = data_appointment.name_appointment
            date.text = data_appointment.date +" | "+ data_appointment.appointment_time
            name_doctor.text = data_appointment.name_doctor
            hn_number.text = "เลขประจำตัวผู้ป่วย :"+" "+data_appointment.hn_number
            hospital.text = data_appointment.hospital
            department.text = data_appointment.department
            email.text = data_appointment.email
            contact_number.text = data_appointment.contact_number



            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            // val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            val currentDateandTime: String = sdf.format(Date())
            var dateCheck: String? = data_appointment.date
            if(dateCheck.equals(currentDateandTime)){
                Log.d(TAG, "date equals--------> =$currentDateandTime")
                //layout_emergency_bg.setBackgroundResource(R.drawable.view_medic_bg_two)
            }else{
                fab.visibility = View.GONE
                date.setTextColor(Color.RED);
            }



        }
    }

}