package com.example.medicomgmester.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DataDoc(var name: String? = null,
                   var name_topic_1: String? = null,
                   var name_topic_2: String? = null,
                   var name_topic_3: String? = null,
                   var name_topic_4: String? = null,
                   var name_topic_5: String? = null,
                   var name_topic_6: String? = null,
                   var name_topic_7: String? = null,
                   var image: String? = null,
                   var image_2: String? = null,
                   var doctor: String? = null,
                   var image_medic: String? = null,
                   var detail_1: String? = null,
                   var detail_2: String? = null,
                   var detail_3: String? = null,
                   var detail_4: String? = null,
                   var detail_5: String? = null,
                   var detail_6: String? = null,
                   var detail_7: String? = null) : Parcelable {
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
        writeString(name)
        writeString(name_topic_1)
        writeString(name_topic_2)
        writeString(name_topic_3)
        writeString(name_topic_4)
        writeString(name_topic_5)
        writeString(name_topic_6)
        writeString(name_topic_7)
        writeString(image)
        writeString(image_2)
        writeString(doctor)
        writeString(image_medic)
        writeString(detail_1)
        writeString(detail_2)
        writeString(detail_3)
        writeString(detail_4)
        writeString(detail_5)
        writeString(detail_6)
        writeString(detail_7)
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