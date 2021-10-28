package com.example.medicomgmester.ui.profile

import android.Manifest
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import co.csadev.kwikpicker.KwikPicker
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.medicomgmester.R
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_profile_image.*
import java.util.ArrayList

class ProfileImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_image)

        setSingleShowButton()
    }

    private fun setSingleShowButton() {
        val singleButton = btn_single_show
        singleButton.setOnClickListener {

        }
    }
}