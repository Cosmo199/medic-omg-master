package com.example.medicomgmester.ui.home.holder

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.model.Appointment
import kotlinx.android.synthetic.main.item_theme_appointment_card.view.*
import java.util.*

class HomeHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(data_appointment: Appointment) {
        itemView.apply {
            name_appointment.text = data_appointment.name_appointment
            date.text = data_appointment.date +" | "+ data_appointment.appointment_time
            name_doctor.text = data_appointment.name_doctor
            hn_number.text = "เลขประจำตัวผู้ป่วย :"+" "+data_appointment.hn_number

            // if check appointment
            var textAppointment : String? = data_appointment.name_appointment
            when {
                textAppointment.equals("นัดถอดสาย") -> {
                    val editor = context.getSharedPreferences("TIME_HOLDER", AppCompatActivity.MODE_PRIVATE).edit()
                    //editor.putString("dateOut", data_appointment.date+" "+data_appointment.appointment_time)
                    editor.putString("dateOutNote", data_appointment.date+" ")
                    editor.putString("dateOut", data_appointment.date_alert+" ")
                    editor.putString("timeOut", data_appointment.appointment_time)
                    editor.apply()
                }
                textAppointment.equals("นัดใส่สาย") -> {
                    val editor = context.getSharedPreferences("TIME_HOLDER", AppCompatActivity.MODE_PRIVATE).edit()
                    //editor.putString("dateInsert", data_appointment.date+" "+data_appointment.appointment_time)
                    editor.putString("dateIntNote", data_appointment.date+" ")
                    editor.putString("dateInsert", data_appointment.date_alert+" ")
                    editor.putString("timeInsert", data_appointment.appointment_time)
                    editor.apply()
                }
                else -> {

                }
            }

        }
    }

}