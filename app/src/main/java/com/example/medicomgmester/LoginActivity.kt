package com.example.medicomgmester

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medicomgmester.handler.EventHandler
import com.example.medicomgmester.models.AnnualEvent
import com.example.medicomgmester.ui.medic.MenuActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setEvent()
        edit_username.setText("Nawakarn")
    }

    private fun setEvent() {
        btnLogin.setOnClickListener {
            var i: String? = edit_username.text.toString()
            val editor = getSharedPreferences("LOGIN_DATA",MODE_PRIVATE).edit()
            editor.putString("name", i)
            editor.apply()
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            //Toast.makeText(this, "คุณชื่อ : $i", Toast.LENGTH_SHORT).show()
        }
    }



}