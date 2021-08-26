package com.example.medicomgmester.ui.home
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicomgmester.LoginActivity
import com.example.medicomgmester.databinding.FragmentHomeBinding
import com.example.medicomgmester.extension.load
import com.example.medicomgmester.model.Appointment
import com.example.medicomgmester.model.ListAppointment
import com.example.medicomgmester.model.ListDiagnosis
import com.example.medicomgmester.model.RememberToken
import com.example.medicomgmester.network.ApiService
import com.example.medicomgmester.ui.home.adapter.AdapterListHome
import kotlinx.android.synthetic.main.fragment_diagnosis.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.load_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        apiService = ApiService()
        val preferences = this.activity?.getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE)
        var getToken: String? = preferences?.getString("remember_token", "ไม่มี Token")
        callApi(getToken)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun callApi(text:String?){
        val call = apiService.appointmentCall(RememberToken(text))
        call.enqueue(object : Callback<ListAppointment> {
            override fun onFailure(call: Call<ListAppointment>, t: Throwable) {
                intentOnClick()
            }
            override fun onResponse(call: Call<ListAppointment>, response: Response<ListAppointment>) {
                val data = response.body()
                val fd: AdapterListHome by lazy { AdapterListHome(listOf()) }
                list_data_appointment?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                list_data_appointment?.isNestedScrollingEnabled = false
                list_data_appointment?.adapter = fd
                data?.results?.let { fd.setItem(it) }
                load_activity.visibility = View.GONE

            }
        })
    }

    private fun intentOnClick() {
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivity(intent)
    }




}