package com.example.medicomgmester.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DataDoc(var type: String? = null,
                   var name: String? = null,
                   var sub_name: String? = null,
                   var image: String? = null,
                   var doctor: String? = null,
                   var suggestion: String? = null,
                   var image_medic: String? = null,
                   var detailData: String? = null,
                   var symptoms: String? = null) : Parcelable {
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
        writeString(type)
        writeString(name)
        writeString(sub_name)
        writeString(image)
        writeString(doctor)
        writeString(suggestion)
        writeString(image_medic)
        writeString(detailData)
        writeString(symptoms)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataDoc> = object : Parcelable.Creator<DataDoc> {
            override fun createFromParcel(source: Parcel): DataDoc = DataDoc(source)
            override fun newArray(size: Int): Array<DataDoc?> = arrayOfNulls(size)
        }
    }
}

data class ListLesson(@SerializedName("data") var results: List<DataDoc>? = null)