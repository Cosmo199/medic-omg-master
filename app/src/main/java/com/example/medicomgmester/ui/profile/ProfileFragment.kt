package com.example.medicomgmester.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.awesomedialog.*
import com.example.medicomgmester.LoginActivity
import com.example.medicomgmester.databinding.FragmentProfileBinding
import com.example.medicomgmester.model.ListProfile
import com.example.medicomgmester.model.RememberToken
import com.example.medicomgmester.network.ApiService
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.load_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiService = ApiService()
        val preferences = this.activity?.getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE)
        var getToken: String? = preferences?.getString("remember_token", "ไม่มี Token")
        callApi(getToken)
        context?.let { callClick(it) }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun callApi(text:String?){
        val call = apiService.profileCall(RememberToken(text))
        call.enqueue(object : Callback<ListProfile> {
            override fun onFailure(call: Call<ListProfile>, t: Throwable) {
                intentOnClick()
            }
            override fun onResponse(call: Call<ListProfile>, response: Response<ListProfile>) {
                val list = response.body()
                name_profile.text = "ชื่อ: "+list?.results?.get(0)?.name
                surname_profile.text = "นามสกุล: "+list?.results?.get(0)?.surname
                sex_profile.text = "เพศ: "+list?.results?.get(0)?.sex
                hn_number_profile.text = "เลขประจำตัวผู้ป่วย: "+list?.results?.get(0)?.hn_number
                age_profile.text = "อายุ: "+list?.results?.get(0)?.age
                education_profile.text = "ระดับการศึกษา: "+list?.results?.get(0)?.education
                email_profile.setText(list?.results?.get(0)?.email)
                tel_profile.setText(list?.results?.get(0)?.tel)
                load_activity.visibility = View.INVISIBLE
            }
        })
    }

    private fun callClick(a: Context){
        btnSaveProfile.setOnClickListener {
            AwesomeDialog.build(a as Activity)
                .title("อัพเดตข้อมูลสำเร็จ")
                .position(AwesomeDialog.POSITIONS.BOTTOM)
                .icon(R.drawable.ic_congrts)
                .body("")
                .onNegative(
                    "ปิด",
                   // buttonBackgroundColor = R.drawable.side_button2
                ) {}
        }
    }

    private fun intentOnClick() {
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivity(intent)
    }


}