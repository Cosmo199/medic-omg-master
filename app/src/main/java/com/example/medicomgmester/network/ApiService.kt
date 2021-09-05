package com.example.medicomgmester.network
import com.example.medicomgmester.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


val BASE_URL= "https://www.urotustent.com/api/"
interface ApiService {


    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body login: Login): Call<ListLogin>

    @Headers("Content-Type: application/json")
    @POST("diagnosis")
    fun diagnosisCall(@Body remember_token: RememberToken): Call<ListDiagnosis>


    @Headers("Content-Type: application/json")
    @POST("appointment")
    fun appointmentCall(@Body remember_token: RememberToken): Call<ListAppointment>

    @Headers("Content-Type: application/json")
    @POST("profile")
    fun profileCall(@Body remember_token: RememberToken): Call<ListProfile>

    companion object{
        operator fun invoke(): ApiService {
            return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

    }

}