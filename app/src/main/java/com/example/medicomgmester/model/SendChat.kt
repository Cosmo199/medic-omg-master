package com.example.medicomgmester.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SendChat(
    val remember_token: String?,
    val txt_Msg: String?
)

data class ResSendChat(
    var chat_msg_message: String? = null,
    var chat_msg_date: String? = null,
    var chat_msg_type: String? = null,
    var messmageBy: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()

    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(chat_msg_message)
        writeString(chat_msg_date)
        writeString(chat_msg_type)
        writeString(messmageBy)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Appointment> = object : Parcelable.Creator<Appointment> {
            override fun createFromParcel(source: Parcel): Appointment = Appointment(source)
            override fun newArray(size: Int): Array<Appointment?> = arrayOfNulls(size)
        }
    }
}


data class ListMessage(@SerializedName("data") var results: List<ResSendChat>? = null)

