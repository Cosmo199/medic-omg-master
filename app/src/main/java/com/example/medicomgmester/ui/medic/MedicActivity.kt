package com.example.medicomgmester.ui.medic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicomgmester.JsonMockUtility
import com.example.medicomgmester.MenuActivity
import com.example.medicomgmester.R
import com.example.medicomgmester.model.ListMedic
import com.example.medicomgmester.ui.medic.adapter.AdapterListMedic
import kotlinx.android.synthetic.main.activity_medic.*
import kotlinx.android.synthetic.main.view_action_bar.*

class MedicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medic)
        mockUpMedicJson()
        setEvent()

    }

    private fun mockUpMedicJson() {
        JsonMockUtility().apply {
            val dataMock = getJsonToMock("medic_list.json", ListMedic::class.java)
            val en: AdapterListMedic by lazy { AdapterListMedic(listOf()) }

            list_medic.layoutManager =
                LinearLayoutManager(this@MedicActivity, LinearLayoutManager.VERTICAL, false)
            list_medic.isNestedScrollingEnabled = false
            list_medic.adapter = en
            dataMock.results?.let { en.setItem(it) }

        }
    }

    private fun setEvent() {
        arrow_back.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

}