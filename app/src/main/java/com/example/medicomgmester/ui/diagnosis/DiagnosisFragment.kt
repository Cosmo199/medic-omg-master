package com.example.medicomgmester.ui.diagnosis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.awesomedialog.*
import com.example.medicomgmester.LoginActivity
import com.example.medicomgmester.databinding.FragmentDiagnosisBinding
import com.example.medicomgmester.extension.load
import com.example.medicomgmester.model.*
import com.example.medicomgmester.network.ApiService
import kotlinx.android.synthetic.main.fragment_diagnosis.*
import kotlinx.android.synthetic.main.load_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiagnosisFragment : Fragment() {

    private var _binding: FragmentDiagnosisBinding? = null
    private val binding get() = _binding!!
    private lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiagnosisBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiService = ApiService()
        val preferences = this.activity?.getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE)
        var getToken: String? = preferences?.getString("remember_token", "ไม่มี Token")
        detail_diagnosis_1.visibility = GONE
        diagnosis.visibility = GONE
        callApi(getToken)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun callApi(text: String?) {
        val call = apiService.diagnosisCall(RememberToken(text))
        call.enqueue(object : Callback<ListDiagnosis> {
            override fun onFailure(call: Call<ListDiagnosis>, t: Throwable) {
                intentOnClick()
                //context?.let { showMessageLogin(it) }
            }

            override fun onResponse(call: Call<ListDiagnosis>, response: Response<ListDiagnosis>) {
                val list = response.body()
                detail_diagnosis_1.visibility = View.VISIBLE
                diagnosis.visibility = View.VISIBLE
                detail_diagnosis_1.text = list?.results?.get(0)?.detail_diagnosis
                var i: String? = list?.results?.get(0)?.kidney_type
                if (i.equals("1")) {
                    image_view_kidney.load("https://urotustent.com/Content/Image/kidney_1.png")
                    name_diagnosis_2.text = "ตำแหน่งที่ต้องใส่สาย: ข้างซ้าย"
                } else if (i.equals("2")) {
                    image_view_kidney.load("https://urotustent.com/Content/Image/kidney_2.png")
                    name_diagnosis_2.text = "ตำแหน่งที่ต้องใส่สาย: ข้างขวา"
                } else {
                    image_view_kidney.load("https://urotustent.com/Content/Image/kidney_3.png")
                    name_diagnosis_2.text = "ตำแหน่งที่ต้องใส่สาย: ทั้ง 2 ข้างซ้าย-ขวา"
                }
                load_activity.visibility = GONE
            }
        })
    }

    private fun intentOnClick() {
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivity(intent)
    }

    private fun showDialogNetwork(a: Context) {
        AwesomeDialog.build(a as Activity)
            .title("การเชื่อมต่อไม่สำเร็จ")
            .position(AwesomeDialog.POSITIONS.CENTER)
            .body("กรุณาตรวจสอบการเชื่อต่ออินเทอร์เน็ต")
            .onNegative(
                "ปิด",
                //buttonBackgroundColor = R.drawable.side_button,
            ) {}
    }
}