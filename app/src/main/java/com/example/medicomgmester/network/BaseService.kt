package com.example.medicomgmester.network

import com.example.medicomgmester.model.ListAppointment
import com.example.medicomgmester.model.ListDiagnosis
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface BaseService {

    @GET("list")
    fun listAppointment(): Observable<Response<ListAppointment>>

    @GET("diagnosis")
    fun listDiagnosis(): Observable<Response<ListDiagnosis>>
    

}