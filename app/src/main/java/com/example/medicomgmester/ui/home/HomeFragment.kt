package com.example.medicomgmester.ui.home
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicomgmester.LoginActivity
import com.example.medicomgmester.databinding.FragmentHomeBinding
import com.example.medicomgmester.model.ListAppointment
import com.example.medicomgmester.model.RememberToken
import com.example.medicomgmester.network.ApiService
import com.example.medicomgmester.notification.NotificationActivity
import com.example.medicomgmester.notification.Utils
import com.example.medicomgmester.ui.home.adapter.AdapterListHome
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.load_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

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
        set_time.visibility = View.GONE
        text_list.visibility = View.GONE
        callApi(getToken)
        setTime()
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
                if(data?.results?.size == 0){
                    set_time.visibility = View.GONE
                    load_activity.visibility = View.GONE
                    text_list.visibility = View.VISIBLE
                } else {
                    load_activity.visibility = View.GONE
                    set_time.visibility = View.VISIBLE
                    text_list.visibility = View.GONE
                    val fd: AdapterListHome by lazy { AdapterListHome(listOf()) }
                    list_data_appointment?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    list_data_appointment?.isNestedScrollingEnabled = false
                    list_data_appointment?.adapter = fd
                    data?.results?.let { fd.setItem(it) }
                    setTimeNotificationDefault()
                }

            }
        })
    }

    private fun intentOnClick() {
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivity(intent)
    }

    private fun setTime(){
        set_time.setOnClickListener {
            val intent = Intent(activity, NotificationActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    private fun setTimeNotificationDefault() {
        //preferencesTimeHolder
        val preferencesTimeHolder = context?.getSharedPreferences("TIME_HOLDER", Context.MODE_PRIVATE)
        var getInsertDate: String? = preferencesTimeHolder?.getString("dateInsert", "noDate")
        var getInsertTime: String? = preferencesTimeHolder?.getString("timeInsert", "noTime")
        var getOutDate: String? = preferencesTimeHolder?.getString("dateOut", "noDate")
        var getOutTime: String? = preferencesTimeHolder?.getString("timeOut", "noTime")

        // Check getInsert
        if (getInsertDate.equals("noDate")) {
        }  else {
            var timeInMilliSeconds: Long = 0
            val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
            val date = sdf.parse(getInsertDate+ getInsertTime)
            timeInMilliSeconds = date.time
            context?.let { Utils.setAlarm(it, timeInMilliSeconds) }
        }

        //Check getOut
        if (getOutDate.equals("noDate")) {
        }  else {
            var timeInMilliSeconds2: Long = 0
            val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
            val date2 = sdf.parse(getOutDate+ getOutTime)
            timeInMilliSeconds2 = date2.time
            context?.let { Utils.setAlarm2(it, timeInMilliSeconds2 ) }
        }
    }
}