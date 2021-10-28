package com.example.medicomgmester

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.medicomgmester.databinding.ActivityMenuBinding
import com.example.medicomgmester.ui.profile.ProfileImageActivity
import kotlinx.android.synthetic.main.nav_header_menu.*

class MenuActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuBinding
    private var doubleBackToExitPressedOnce = false

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
        val share = getSharedPreferences("LOGIN_DATA", MODE_PRIVATE)
        var i: String? = share.getString("name", "ไม่มีชื่อ")
        username.text = i
        /*
        profile_image_nav_header.setOnClickListener {
            val intent = Intent(this, ProfileImageActivity::class.java)
            startActivity(intent)
        }
         */
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


}