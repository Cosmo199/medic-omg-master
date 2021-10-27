package com.example.medicomgmester.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UpdateProfile(
    val remember_token: String?,
    val email: String?,
    val tel: String?
)

data class ProfileUpdate(
    var message: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(message)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataMedic> = object : Parcelable.Creator<DataMedic> {
            override fun createFromParcel(source: Parcel): DataMedic = DataMedic(source)
            override fun newArray(size: Int): Array<DataMedic?> = arrayOfNulls(size)
        }
    }
}

data class ListProfileUpdate(@SerializedName("data") var results: List<ProfileUpdate>? = null)