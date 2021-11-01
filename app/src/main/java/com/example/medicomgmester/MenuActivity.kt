package com.example.medicomgmester

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.medicomgmester.databinding.ActivityMenuBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.nav_header_menu.*


class MenuActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuBinding
    private var doubleBackToExitPressedOnce = false
    private val PICK_IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMenu.toolbar)

        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_menu)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_diagnosis,
                R.id.nav_gallery,
                R.id.nav_article,
                R.id.nav_profile,
                R.id.nav_slideshow
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        getShare()
        profile_image_nav_header.setOnClickListener {
          //  imageProfileHolder()
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_menu)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = false
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            //doubleBackToExitPressedOnce = false
        }, 2000)
    }

    private fun imageProfileHolder(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {
            val selectedImageUri: Uri? = data?.data
            val txt: String? =data?.data.toString()
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
            val bitMapString: String?=bitmap.toString()
            val uriData = getSharedPreferences("IMAGE_PROFILE", MODE_PRIVATE).edit()
            uriData.putString("uri",txt)
            uriData.putString("bitmap",bitMapString)
            uriData.apply()
            profile_image_nav_header.setImageBitmap(bitmap)
        }
    }

    private fun getShare(){
        val share = getSharedPreferences("LOGIN_DATA", MODE_PRIVATE)
        var i: String? = share.getString("name", "ไม่มีชื่อ")
        username.text = i
        val uriShare = getSharedPreferences("IMAGE_PROFILE", MODE_PRIVATE)
        val imageUri: String? = uriShare.getString("uri", "noUri")
        val myUri: Uri = Uri.parse(imageUri)
        val getBitmap: String? = uriShare.getString("bitmap", "noUri")

        if(imageUri.equals("noUri")){
            profile_image_nav_header.setImageResource(R.drawable.user)
        }else{
            profile_image_nav_header.setImageResource(R.drawable.user)
            // profile_image_nav_header.setImageBitmap(image)
        }
    }

}
