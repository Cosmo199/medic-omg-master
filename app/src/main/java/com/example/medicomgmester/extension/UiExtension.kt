package com.example.medicomgmester.extension
import android.widget.ImageView
import com.example.medicomgmester.R

infix fun ImageView.load(url: String?) = this.apply {
 com.bumptech.glide.Glide.with(context).load(url).placeholder(R.drawable.kidney_3).into(this)

}








