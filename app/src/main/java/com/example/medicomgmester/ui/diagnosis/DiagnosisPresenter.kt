package com.example.medicomgmester.ui.diagnosis
import com.example.medicomgmester.InterActor
import com.example.medicomgmester.call.Request
import com.example.medicomgmester.model.ListDiagnosis

class DiagnosisPresenter (val view: DiagnosisContact.View) : DiagnosisContact.Presenter, Request.ResponseList {

    private val actData: InterActor.ActData = Request()

    override fun callList(key: String?) { actData.callListDiagnosis(this) }

    override fun <T> onSuccess(t: T) { view.onCallSuccess(data = (t as ListDiagnosis).results) }
}

