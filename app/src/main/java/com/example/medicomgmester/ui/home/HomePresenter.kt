package com.example.medicomgmester.ui.home

import com.example.medicomgmester.InterActor
import com.example.medicomgmester.call.Request
import com.example.medicomgmester.model.ListAppointment

class HomePresenter (val view: HomeContact.View) : HomeContact.Presenter, Request.ResponseList {

    private val actData: InterActor.ActData = Request()

    override fun callList(key: String?) {
     actData.callListAppointment(this)
    }

    override fun <T> onSuccess(t: T) {
        view.onCallSuccess(data = (t as ListAppointment).results)
    }
}

