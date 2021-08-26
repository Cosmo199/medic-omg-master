package com.example.medicomgmester.model
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Diagnosis(
    var kidney_type: String? = null,
    var detail_diagnosis: String? = null) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(kidney_type)
        writeString(detail_diagnosis)


    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Diagnosis> = object : Parcelable.Creator<Diagnosis> {
            override fun createFromParcel(source: Parcel): Diagnosis = Diagnosis(source)
            override fun newArray(size: Int): Array<Diagnosis?> = arrayOfNulls(size)
        }
    }
}

data class ListDiagnosis(@SerializedName("data") var results: List<Diagnosis>? = null)