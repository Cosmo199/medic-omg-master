package com.example.medicomgmester.ui.home

import com.example.medicomgmester.model.Appointment


interface HomeContact {

    interface Presenter {
        fun callList(key: String? = null)
    }
    interface View {
        fun onCallSuccess(data: List<Appointment>?)
    }
}