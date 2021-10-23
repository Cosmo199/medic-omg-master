package com.example.medicomgmester.model
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Appointment(
    var name_appointment: String? = null,
    var detail_appointment: String? = null,
    var date: String? = null,
    var date_alert: String? =null,
    var appointment_time: String? = null,
    var name_doctor: String? = null,
    var hn_number: String? = null,
    var hospital: String? = null,
    var department: String? = null,
    var email: String? = null,
    var contact_number: String? = null) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name_appointment)
        writeString(detail_appointment)
        writeString(date)
        writeString(date_alert)
        writeString(appointment_time)
        writeString(name_doctor)
        writeString(hn_number)
        writeString(hospital)
        writeString(department)
        writeString(email)
        writeString(contact_number)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Appointment> = object : Parcelable.Creator<Appointment> {
            override fun createFromParcel(source: Parcel): Appointment = Appointment(source)
            override fun newArray(size: Int): Array<Appointment?> = arrayOfNulls(size)
        }
    }
}

data class ListAppointment(@SerializedName("data") var results: List<Appointment>? = null)