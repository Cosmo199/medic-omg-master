package com.example.medicomgmester

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medicomgmester.ui.medic.MenuActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        setEvent()
        edit_username.setText("Nawakarn")

    }
    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    private fun setEvent() {
        btnLogin.setOnClickListener {
            btnLogin.isEnabled = false
            var i: String? = edit_username.text.toString()
            val editor = getSharedPreferences("LOGIN_DATA", MODE_PRIVATE).edit()
            editor.putString("name", i)
            editor.apply()
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showToast(isConnected)
    }


    private fun showToast(isConnected: Boolean) {
        if (!isConnected) {
            Toast.makeText(this, "กรุณาต่อ internet", Toast.LENGTH_LONG).show()
        } else {
            if (networkType()) {
                Toast.makeText(
                    this,
                    "Connected to Wifi Network",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Connected to Cellular Network",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun networkType(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isWifi: Boolean = activeNetwork?.type == ConnectivityManager.TYPE_WIFI
        return isWifi
    }

}