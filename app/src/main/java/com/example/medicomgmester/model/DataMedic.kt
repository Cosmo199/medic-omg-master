package com.example.medicomgmester.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DataMedic(
    var name_prefix: String? = null,
    var name: String? = null,
    var hospital: String? = null,
    var department: String? = null,
    var contact_tel: String? = null,
    var contact_email: String? = null,
    var image: String? = null,
    var mock: String? = null) : Parcelable {
    constructor(source: Parcel) : this(
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
        writeString(name_prefix)
        writeString(name)
        writeString(hospital)
        writeString(department)
        writeString(contact_tel)
        writeString(contact_email)
        writeString(image)
        writeString(mock)

    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataMedic> = object : Parcelable.Creator<DataMedic> {
            override fun createFromParcel(source: Parcel): DataMedic = DataMedic(source)
            override fun newArray(size: Int): Array<DataMedic?> = arrayOfNulls(size)
        }
    }
}

data class ListMedic(@SerializedName("data") var results: List<DataMedic>? = null)