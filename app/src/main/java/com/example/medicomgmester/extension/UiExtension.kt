package com.example.medicomgmester.extension
import android.widget.ImageView


infix fun ImageView.load(url: String?) = this.apply {
 com.bumptech.glide.Glide.with(context).load(url).into(this)
}







