package com.example.medicomgmester.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Login(
    val username: String?,
    val password: String?
)

data class ResLogin(
    var id: String? = null,
    var message: String? = null,
    var remember_token: String? = null,
    var name: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()

    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
        writeString(message)
        writeString(remember_token)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Appointment> = object : Parcelable.Creator<Appointment> {
            override fun createFromParcel(source: Parcel): Appointment = Appointment(source)
            override fun newArray(size: Int): Array<Appointment?> = arrayOfNulls(size)
        }
    }
}


data class ListLogin(@SerializedName("data") var results: List<ResLogin>? = null)

