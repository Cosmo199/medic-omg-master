package com.example.medicomgmester.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Emergency(
    var name_department: String? = null,
    var line: String? = null,
    var contact_number: String? = null,
    var image: String? = null,
    var lat_location: String? = null,
    var long_location: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()

    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name_department)
        writeString(line)
        writeString(contact_number)
        writeString(image)
        writeString(lat_location)
        writeString(long_location)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataDoc> = object : Parcelable.Creator<DataDoc> {
            override fun createFromParcel(source: Parcel): DataDoc = DataDoc(source)
            override fun newArray(size: Int): Array<DataDoc?> = arrayOfNulls(size)
        }
    }
}

data class ListEmergency(@SerializedName("data") var results: List<Emergency>? = null)