package com.example.medicomgmester.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Profile(
    var age: String? = null,
    var education: String? = null,
    var email: String? = null,
    var hn_number: String? = null,
    var username: String? = null,
    var name: String? = null,
    var surname: String? = null,
    var sex: String? = null,
    var tel: String? = null) : Parcelable {
    constructor(source: Parcel) : this(
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
        writeString(age)
        writeString(education)
        writeString(email)
        writeString(hn_number)
        writeString(username)
        writeString(name)
        writeString(surname)
        writeString(sex)
        writeString(tel)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataMedic> = object : Parcelable.Creator<DataMedic> {
            override fun createFromParcel(source: Parcel): DataMedic = DataMedic(source)
            override fun newArray(size: Int): Array<DataMedic?> = arrayOfNulls(size)
        }
    }
}

data class ListProfile(@SerializedName("data") var results: List<Profile>? = null)