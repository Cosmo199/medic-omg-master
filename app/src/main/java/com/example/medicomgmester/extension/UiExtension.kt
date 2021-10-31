package com.example.medicomgmester.extension
import android.net.Uri
import android.widget.ImageView

infix fun ImageView.loadUri(url: Uri) = this.apply {
 com.bumptech.glide.Glide.with(context).load(url).into(this)
}

infix fun ImageView.load(url: String?) = this.apply {
 com.bumptech.glide.Glide.with(context).load(url).into(this)
}








