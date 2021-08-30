package com.example.medicomgmester.ui.emergency

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicomgmester.JsonMockUtility
import com.example.medicomgmester.R
import com.example.medicomgmester.model.ListEmergency
import com.example.medicomgmester.ui.emergency.adapter.AdapterListEmergency
import com.example.medicomgmester.MenuActivity
import kotlinx.android.synthetic.main.activity_emergency.*
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.view_action_bar.*

class EmergencyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)
        setUI()
        serEvent()
        mockUpJson()
    }

    private fun serEvent(){
        arrow_back.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUI(){
        text_bar.text = "เบอร์ติดต่อภายใน"
    }

    private fun mockUpJson(){
        JsonMockUtility().apply {
            val dataMock = getJsonToMock("emergency.json", ListEmergency::class.java)
            val en: AdapterListEmergency by lazy { AdapterListEmergency(listOf()) }
            list_emergency?.layoutManager =
                LinearLayoutManager(this@EmergencyActivity, LinearLayoutManager.VERTICAL, false)
            list_emergency?.isNestedScrollingEnabled = false
            list_emergency?.adapter = en
            dataMock.results?.let { en.setItem(it) }
        }
    }
}