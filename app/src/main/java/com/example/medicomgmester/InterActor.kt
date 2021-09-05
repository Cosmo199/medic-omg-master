package com.example.medicomgmester

import com.example.medicomgmester.call.Request

class InterActor {

    interface ActData {
        fun callListAppointment(callback: Request.ResponseList)
        fun callListDiagnosis(callback: Request.ResponseList)
        fun callLogin(callback: Request.ResponseList)
    }


}