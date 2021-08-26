package com.example.medicomgmester.call


import com.example.medicomgmester.InterActor
import com.example.medicomgmester.model.ListAppointment
import com.example.medicomgmester.model.ListDiagnosis
import com.example.medicomgmester.network.BaseRetrofit
import com.example.medicomgmester.network.BaseUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class Request : InterActor.ActData {

    interface ResponseList {
        fun <T> onSuccess(t: T)
    }


    override fun callListAppointment(callback: ResponseList) {
        val baseService by lazy { BaseRetrofit.createRx(BaseUrl.baseUrl) }
        baseService?.listAppointment()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : DisposableObserver<Response<ListAppointment>>() {
                override fun onComplete() {}
                override fun onNext(t: Response<ListAppointment>) {
                    t.body()?.let { callback.onSuccess(it) }
                }

                override fun onError(e: Throwable) {}
            })
    }

    override fun callListDiagnosis(callback: ResponseList) {
        val baseService by lazy { BaseRetrofit.createRx(BaseUrl.baseUrl) }
        baseService?.listDiagnosis()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : DisposableObserver<Response<ListDiagnosis>>() {
                override fun onComplete() {}
                override fun onNext(t: Response<ListDiagnosis>) {
                    t.body()?.let { callback.onSuccess(it) }
                }

                override fun onError(e: Throwable) {}
            })


    }

    override fun callLogin(callback: ResponseList) {

    }


}