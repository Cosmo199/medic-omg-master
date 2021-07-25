package com.example.medicomgmester.ui.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicomgmester.JsonMockUtility
import com.example.medicomgmester.databinding.FragmentHomeBinding
import com.example.medicomgmester.model.ListAppointment
import com.example.medicomgmester.ui.home.adapter.AdapterListHome
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mockUpJson()
    }

    private fun mockUpJson(){
        JsonMockUtility().apply {
            val dataMock = getJsonToMock("appointment.json", ListAppointment::class.java)
            val en: AdapterListHome by lazy { AdapterListHome(listOf()) }
            list_data_appointment?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            list_data_appointment?.isNestedScrollingEnabled = false
            list_data_appointment?.adapter = en
            dataMock.results?.let { en.setItem(it) }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}