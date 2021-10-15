package com.example.medicomgmester.ui.diagnosis

import com.example.medicomgmester.model.Diagnosis

interface DiagnosisContact {

    interface Presenter {
        fun callList(key: String? = null)
    }
    interface View {
        fun onCallSuccess(data: List<Diagnosis>?)
    }
}