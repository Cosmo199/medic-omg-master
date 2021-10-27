package com.example.medicomgmester.ui.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicomgmester.MenuActivity
import com.example.medicomgmester.R
import com.example.medicomgmester.model.*
import com.example.medicomgmester.network.ApiService
import com.example.medicomgmester.ui.chat.adapter.AdapterChatList
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_action_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.schedule


class ChatActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        apiService = ApiService()
        val preferences = getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE)
        var getToken: String? = preferences?.getString("remember_token", "ไม่มี Token")
        setEvent()
        callApi(getToken)
        sendMessage(getToken)
    }

    private fun setEvent() {
        text_bar.text = "แชท"
        arrow_back.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

    }

    private fun callApi(token: String?) {
        val call = apiService.chatListCall(RememberToken(token))
        call.enqueue(object : Callback<ListChat> {
            override fun onFailure(call: Call<ListChat>, t: Throwable) {}
            override fun onResponse(call: Call<ListChat>, response: Response<ListChat>) {
                val data = response.body()
                val fd: AdapterChatList by lazy { AdapterChatList(listOf()) }
                list_meg?.layoutManager =
                    LinearLayoutManager(this@ChatActivity, LinearLayoutManager.VERTICAL, false)
                list_meg?.isNestedScrollingEnabled = false
                list_meg?.adapter = fd
                data?.results?.let { fd.setItem(it) }
                data?.results?.size?.minus(1)?.let { list_meg.scrollToPosition(it) }
            }
        })
    }

    private fun sendMessage(token: String?) {
        btnSend.setOnClickListener {
            when (text_input.text.toString()) {
                "" -> {
                    Toast.makeText(this, "กรุณากรอกข้อความ", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val data: String = text_input.text.toString()
                    callApiSendMessage(token,data)
                }
            }
        }
    }

    private fun callApiSendMessage(token: String?,editMessage: String?){
        val call = apiService.sendMessage(SendChat(token, editMessage))
        call.enqueue(object : Callback<ListMessage> {
            override fun onFailure(call: Call<ListMessage>, t: Throwable) {}

            override fun onResponse(call: Call<ListMessage>, response: Response<ListMessage>) {
                if (response.isSuccessful) {
                    text_input.setText("")
                    val preferences = getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE)
                    var getToken: String? = preferences?.getString("remember_token", "ไม่มี Token")
                    callApi(getToken)
                    autoReload()
                }
            } })
    }

    private fun autoReload(){
        object : CountDownTimer(40000, 2000) {
            override fun onTick(millisUntilFinished: Long) {
                val preferences = getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE)
                var getToken: String? = preferences?.getString("remember_token", "ไม่มี Token")
                callApi(getToken)
            }
            override fun onFinish() {
            }
        }.start()

    }

}