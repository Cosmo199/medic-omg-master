package com.example.medicomgmester

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.awesomedialog.*
import com.example.medicomgmester.model.ListLogin
import com.example.medicomgmester.model.Login
import com.example.medicomgmester.network.ApiService
import com.tommasoberlose.progressdialog.ProgressDialogFragment
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    private lateinit var apiService: ApiService
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
        apiService = ApiService()
        registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        setEvent()
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    private fun setEvent() {
        val preferences = getSharedPreferences("USERNAME", Context.MODE_PRIVATE)
        var userPreferences: String? = preferences?.getString("user", "")
        var passPreferences: String? = preferences?.getString("pass", "")
        edit_username.setText(userPreferences)
        edit_password.setText(passPreferences)

        btnLogin.setOnClickListener {
            val user: String = edit_username.text.toString()
            val pass: String = edit_password.text.toString()
            when {
                user == "" -> {
                    showMessageLogin()
                }
                pass == "" -> {
                    showMessageLogin()
                }
                else -> {
                    ProgressDialogFragment.showProgressBar(this)
                    callApi(user, pass)
                    val editor = getSharedPreferences("USERNAME", MODE_PRIVATE).edit()
                    editor.putString("user", user)
                    editor.putString("pass", pass)
                    editor.apply()
                }
            }
        }

        text_policy.setOnClickListener {
            val intent = Intent(this, PolicyActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showToast(isConnected)
    }

    private fun showToast(isConnected: Boolean) {
        if (!isConnected) {
            showDialogNetwork()
        } else {
            if (networkType()) { } else { }
        }
    }

    private fun networkType(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isWifi: Boolean = activeNetwork?.type == ConnectivityManager.TYPE_WIFI
        return isWifi
    }

    private fun callApi(editUsername: String, editPassword: String) {
        val call = apiService.login(Login(editUsername, editPassword))
        call.enqueue(object : Callback<ListLogin> {
            override fun onFailure(call: Call<ListLogin>, t: Throwable) {
                showMessageLogin()
            }

            override fun onResponse(call: Call<ListLogin>, response: Response<ListLogin>) {
                if (response.isSuccessful) {
                    val list = response.body()
                    var i: String? = list?.results?.get(0)?.name
                    var j: String? = list?.results?.get(0)?.remember_token
                    val editor = getSharedPreferences("LOGIN_DATA", MODE_PRIVATE).edit()
                    editor.putString("name", i)
                    editor.putString("remember_token", j)
                    editor.apply()
                    intentOnClick()
                }
            }
        })

    }

    private fun intentOnClick() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    private fun showMessageLogin() {
        ProgressDialogFragment.hideProgressBar(this)
        AwesomeDialog.build(this)
            .title("เข้าสู่ระบบไม่สำเร็จ")
            .position(AwesomeDialog.POSITIONS.CENTER)
            .body("ชื่อผู้ใช้ กับ รหัสผ่านไม่ถูกต้อง")
            .onNegative(
                "ปิด",
                buttonBackgroundColor = R.drawable.side_button,
            ) {}
    }

    private fun showDialogNetwork() {
        AwesomeDialog.build(this)
            .title("การเชื่อมต่อไม่สำเร็จ")
            .position(AwesomeDialog.POSITIONS.CENTER)
            .body("กรุณาตรวจสอบการเชื่อต่ออินเทอร์เน็ต")
            .onNegative(
                "ปิด",
                buttonBackgroundColor = R.drawable.side_button,
            ) {}
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "กดอีกครั้งเพื่อปิด app", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            moveTaskToBack(true);
            exitProcess(-1)
           // doubleBackToExitPressedOnce = false
        }, 1000)
    }

}