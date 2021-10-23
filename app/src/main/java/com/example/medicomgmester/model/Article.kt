package com.example.medicomgmester.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Article(
    var id: String? = null,
    var article_name: String? = null,
    var article_date: String? = null,
    var image_01: String? = null,
    var image_02: String? = null,
    var detail: String? = null,
    var author: String? = null,
    var date: String? = null,
    var time: String? = null
) : Parcelable {
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
        writeString(id)
        writeString(article_name)
        writeString(article_date)
        writeString(image_01)
        writeString(image_02)
        writeString(detail)
        writeString(author)
        writeString(date)
        writeString(time)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Article> = object : Parcelable.Creator<Article> {
            override fun createFromParcel(source: Parcel): Article = Article(source)
            override fun newArray(size: Int): Array<Article?> = arrayOfNulls(size)
        }
    }
}

data class ListArticle(@SerializedName("data") var results: List<Article>? = null)